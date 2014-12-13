
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
	
	$scope.setProject =function(newProject){
	    $scope.project = newProject;
	};

}
]);


fixItApp.controller('EditProjectController', ['ProjectService', '$scope', 'project', function (ProjectService, $scope, project) {
	
    $scope.project = project;
    
    $scope.saveProject = function() {
		ProjectService.save($scope.project).then(function (data) {
		    $scope.project = data;
         });
    }

    $scope.removeCard = function(index) {
    	$scope.project.cards.splice(index, 1);
    }
    	
}
]);

fixItApp.controller('EditItemCardController', ['ProjectService', '$scope', function (ProjectService, $scope) {

    $scope.itemCard = {type : 'item'};

    $scope.addCard = function() {
        $scope.project.cards.push($scope.itemCard);
    };

    $scope.provide = function(project, card) {
        console.log("Provide an item projectId=" + project.id + " cardId=" + card.id);
        ProjectService.provide(project, card, 1).then(function (data) {
            $scope.setProject(data);
        });

    };

    $scope.cancelContribution = function(project, contribution) {
        console.log("Cancel a contribution");
        ProjectService.cancelContribution(project, contribution).then(function (data) {
            $scope.setProject(data);
        });

    };
}
]);

