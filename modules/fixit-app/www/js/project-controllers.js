
angular.module('fixit').controller('FixItController', ['$scope', '$window', '$cordovaToast', function ($scope, $window,  $cordovaToast) {
	$scope.toastMe = function(message) {
	    
	    $cordovaToast.show(message, 'short', 'center').then(
		    function(success) {
			// success
		    }, function(error) {
			// error
		    });
	    
	    if (window.cordova) {
		    $cordovaToast.show('Youpi', 'short', 'center').then(
			    function(success) {
				// success
			    }, function(error) {
				// error
			    });
	    }
	}
	    	  
}
]);


angular.module('fixit').controller('ListProjectController', ['ProjectService', '$scope', 'projects', '$window', function (ProjectService, $scope, projects, $window) {
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


angular.module('fixit').controller('EditProjectController', ['ProjectService', '$scope', '$state', '$ionicPopup', 'project', function (ProjectService, $scope, $state, $ionicPopup, project) {
	
    $scope.project = project;
    
    $scope.saveProject = function(projectToSave) {
		ProjectService.saveProject(projectToSave).then(function (data) {
		    $scope.project = data;
         });
    }

	$scope.createProject = function(projectToSave) {
		ProjectService.saveProject(projectToSave).then(function (data) {
			$scope.project = data;
			$scope.toastMe('Project ' + projectToSave.name + ' updated.');
			$state.go('app.project-edit', {projectId: $scope.project.id});
		});
	}

	$scope.publishProject = function(projectToPublish) {
		ProjectService.publishProject(projectToPublish).then(function (data) {
			$scope.project = data;
			$scope.toastMe('Project ' + projectToPublish.name + ' published.');
		});
	}

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
	}

    $scope.removeCard = function(index) {
    	$scope.project.cards.splice(index, 1);
    }
    	
}
]);

