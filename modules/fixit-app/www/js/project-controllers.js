

angular.module('fixit').controller('ListProjectController', ['ProjectService', '$scope', 'projects', '$window',
	function (ProjectService, $scope, projects, $window) {
		console.log('Calling ListProjectController');

		$scope.projects = projects;

		$scope.doRefresh = function() {
			var username = localStorage.getItem("username");
			console.log('doRefresh' + username);
			ProjectService.getProjectsByOwner(username).then(function (data) {
				$scope.projects = data;
			});
			//Stop the ion-refresher from spinning
			$scope.$broadcast('scroll.refreshComplete');
		};

	}
]);



angular.module('fixit').controller('ViewProjectController', ['$scope', 'project', function ($scope, project) {

	$scope.project = project;

	$scope.setProject =function(newProject){
		$scope.project = newProject;
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
				});
			};

			$scope.createProject = function(projectToSave) {
				ProjectService.saveProject(projectToSave).then(function (data) {
					$scope.project = data;
					$scope.toastMe('Project ' + projectToSave.name + ' updated.');
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
						ProjectService.deleteProject(projectToDelete).then(function (data) {
							$scope.toastMe('Project ' + projectToDelete.name + ' deleted.');
							$state.go('app.project-new');
						});
					}
				});
			};

			$scope.removeCard = function(index) {
				$scope.project.cards.splice(index, 1);
			};

			// Triggered on a button click, or some other target
			$scope.showAction = function(projectToUpdate) {
				// Show the action sheet
				var hideSheet = $ionicActionSheet.show({
					buttons: [
						{ text: 'Update' },
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


		}
	]);

