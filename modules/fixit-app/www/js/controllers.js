
fixItApp.controller('MyProjectController', ['ProjectService', '$scope', 'projects', function (ProjectService, $scope, projects) {
	console.log('Calling MyProjectController');
	$scope.projects = projects;
    }
]);


fixItApp.controller('ProjectController', ['ProjectService', '$scope', '$http', function (ProjectService, $scope, $http) {
	 
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

fixItApp.controller('ViewProjectController', ['$scope', 'project', function ($scope, project) {

	$scope.project = project;

}
]);
