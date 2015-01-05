
angular.module('fixit').controller('MyProjectController', ['ProjectService', '$scope', 'projects', function (ProjectService, $scope, projects) {
	console.log('Calling MyProjectController');
	$scope.projects = projects;
    }
]);


angular.module('fixit').controller('ProjectController', ['ProjectService', '$scope', '$http', function (ProjectService, $scope, $http) {
	 
    $scope.init = function()
	{
    	$scope.initVar = 'InitOk';
    	console.log('Calling init');
	    //This function is sort of private constructor for controller
        ProjectService.getProjects().then(function (data) {
            $scope.projects = data;
        });
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
			$state.go('app.project-edit', {projectId: $scope.project.id});
		});
	}

	$scope.publishProject = function(projectToPublish) {
		ProjectService.publishProject(projectToPublish).then(function (data) {
			$scope.project = data;
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

