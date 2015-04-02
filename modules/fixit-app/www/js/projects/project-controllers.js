

angular.module('fixit').controller('MyProjectController',
	['ProjectService', '$scope',
		function (ProjectService, $scope) {

			ProjectService.getProjectsByOwner($scope.getUsername()).then(function (projects) {
				console.log("MyProjectController-2 getProjectsByOwner is called ");
				$scope.projects = projects;
			});

			$scope.doRefresh = function() {
				ProjectService.getProjectsByOwner($scope.getUsername()).then(function (projects) {
					$scope.projects = projects;
				});
				$scope.$broadcast('scroll.refreshComplete');
			};

		}
	]);

angular.module('fixit').controller('UserProjectController',
	['ProjectService', '$scope', 'username',
		function (ProjectService, $scope, username) {

			ProjectService.getProjectsByOwner(username).then(function (projects) {
				$scope.projects = projects;
			});

			$scope.doRefresh = function() {
				ProjectService.getProjectsByOwner(username).then(function (projects) {
					$scope.projects = projects;
				});
				$scope.$broadcast('scroll.refreshComplete');
			};

		}
	]);



angular.module('fixit').controller('DiscoverProjectController',
	['ProjectService', '$scope', 'projects',
		function (ProjectService, $scope, projects) {

			$scope.projects = projects;

			$scope.doRefresh = function() {
				ProjectService.getProjects().then(function (data) {
					$scope.projects = data;
				});
				//Stop the ion-refresher from spinning
				$scope.$broadcast('scroll.refreshComplete');
			};

		}
	]);

angular.module('fixit').controller('ViewProjectController',
	['$scope', '$ionicPopup', 'ProjectService', 'projectId',
		function ($scope, $ionicPopup, ProjectService, projectId) {
			console.log("ViewProjectController projectId=" + projectId);


			$scope.summary = {followerSize : 0, commentSize : 0, comments: []};

			ProjectService.getProject(projectId).then(function (project) {
				console.log("ViewProjectController getProject is called projectId=" + projectId);
				$scope.project = project;
				ProjectService.getFollowerSize(projectId).then(function (data) {
					$scope.summary.followerSize = data;
				});

				ProjectService.getCommentSize(projectId).then(function (data) {
					$scope.summary.commentSize = data;
				});

				ProjectService.getComments(projectId).then(function (data) {
					$scope.summary.comments = data;
				});

			});

			$scope.comment = {projectId: projectId, content: ''};

			$scope.postComment = function(comment) {
				ProjectService.postComment(comment.projectId, comment.content).then(function (comment) {
					$scope.summary.comments.push(comment);
					$scope.comment = {projectId: projectId, content: ''};
					ProjectService.getCommentSize(projectId).then(function (data) {
						$scope.summary.commentSize = data;
					});
				});
			};


			$scope.setProject =function(newProject){
				$scope.project = newProject;
				ProjectService.getFollowerSize(projectId).then(function (data) {
					$scope.summary.followerSize = data;
				});
				ProjectService.getCommentSize(projectId).then(function (data) {
					$scope.summary.commentSize = data;
				});
				ProjectService.getComments(projectId).then(function (data) {
					$scope.summary.comments = data;
				});
			};

			$scope.follow = function(project){
				ProjectService.followProject(project).then(function (favorites) {
					$scope.setFavorites(favorites);
					ProjectService.getFollowerSize(projectId).then(function (data) {
						$scope.summary.followerSize = data;
					});
					$scope.toastMe('Project ' + project.name + ' is added to your favorites.');
				});
			};

			$scope.unfollow = function(project){
				var confirmPopup = $ionicPopup.confirm({
					title: 'Unfollow',
					template: 'Remove project ' + project.name + ' from favorites ?'
				});
				confirmPopup.then(function(res) {
					if(res) {
						ProjectService.unfollowProject(project).then(function (favorites) {
							$scope.setFavorites(favorites);
							ProjectService.getFollowerSize(projectId).then(function (data) {
								$scope.summary.followerSize = data;
							});
							$scope.toastMe('Project ' + project.name + ' is removed from your favorites.');
						});
					}
				});
			};


		}
	]);


angular.module('fixit').controller('EditProjectController',
	['ProjectService', '$scope', '$state', '$ionicPopup', '$ionicActionSheet', 'project',
		function (ProjectService, $scope, $state, $ionicPopup, $ionicActionSheet, project) {

			$scope.project = project;

			$scope.saveProject = function(projectToSave) {
				ProjectService.saveProject(projectToSave).then(function (data) {
					$scope.project = data;
					$scope.toastMe('Project ' + projectToSave.name + ' updated!');
					$state.go('app.project-single', {projectId: project.id});
				});
			};

			$scope.createProject = function(projectToSave) {
				ProjectService.saveProject(projectToSave).then(function (data) {
					$scope.project = data;
					$scope.toastMe('Project ' + projectToSave.name + ' created!');
					$state.go('app.project-edit', {projectId: $scope.project.id});
				});
			};

			$scope.publishProject = function(projectToPublish) {
				ProjectService.publishProject(projectToPublish).then(function (data) {
					$scope.project = data;
					$scope.toastMe('Project ' + projectToPublish.name + ' published.');
				});
			};

			$scope.deleteProject = function(projectToDelete) {
				var confirmPopup = $ionicPopup.confirm({
					title: 'Delete Project',
					template: 'Are you sure you want to delete this project?'
				});
				confirmPopup.then(function(res) {
					if(res) {
						ProjectService.deleteProject(projectToDelete).then(function () {
							$scope.toastMe('Project ' + projectToDelete.name + ' deleted.');
							$state.go('app.project-new');
						});
					}
				});
			};

			$scope.removeCard = function(index) {
				$scope.project.cards.splice(index, 1);
			};

			$scope.addNewCard = function(project) {
				console.log('addNewCard');
				$state.go('app.card-selector', {projectId: project.id});
			};

			// Triggered on a button click, or some other target
			$scope.showAction = function(projectToUpdate) {
				// Show the action sheet
				$ionicActionSheet.show({
					buttons: [
						{ text: 'Apply' },
						{ text: 'Publish' }
					],
					destructiveText: 'Delete',
					titleText: 'Update your project',
					cancelText: 'Cancel',
					destructiveButtonClicked: function() {
						$scope.deleteProject(projectToUpdate);
					},
					buttonClicked: function(index) {
						if (index == 0){
							$scope.saveProject(projectToUpdate);
						} else if (index == 1) {
							$scope.publishProject(projectToUpdate);
						}
						return true;
					}
				});
			};


			$scope.data = {
				showReorder: false,
				showDelete: false
			};

			$scope.reorderCard = function(card, fromIndex, toIndex) {

				console.log("reorderCard fromIndex = " + fromIndex + ' toIndex=' + toIndex);

				$scope.project.cards.splice(fromIndex, 1)
				$scope.project.cards.splice(toIndex, 0, card)
			}

		}
	]);



angular.module('fixit').controller('ProjectFollowersController', ['ProjectService', '$scope', 'projectId',
	function (ProjectService, $scope, projectId) {

		ProjectService.getFollowers(projectId).then(function (followers) {
			$scope.followers = followers;
		});

	}
]);

angular.module('fixit').controller('ProjectContributionsController', ['ProjectService', '$scope', 'projectId',
	function (ProjectService, $scope, projectId) {

		ProjectService.getContributions(projectId).then(function (contributions) {
			$scope.contributions = contributions;
		});

	}
]);

angular.module('fixit').controller('ProjectCommentsController', ['ProjectService', '$scope', 'projectId',
	function (ProjectService, $scope, projectId) {

		$scope.comment = {projectId: projectId, content: ''};

		ProjectService.getComments(projectId).then(function (comments) {
			$scope.comments = comments;
		});


		$scope.postComment = function(comment) {
			ProjectService.postComment(comment.projectId, comment.content).then(function (comment) {
				$scope.comments.push(comment);
				$scope.comment = {projectId: projectId, content: ''};
			});
		};





	}
]);