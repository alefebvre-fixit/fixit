
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


angular.module('fixit').controller('EditProjectController', ['ProjectService', '$scope', 'project', function (ProjectService, $scope, project) {
	
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

angular.module('fixit').controller('CardSelectorController', ['ProjectService', '$scope', 'project', function (ProjectService, $scope, project) {
    console.log('Calling CardSelectorController');

    $scope.project = project;

}
]);


angular.module('fixit').controller('CardController', ['$scope', 'card', 'project', function ($scope, card, project) {

    $scope.card = card;
    $scope.project = project;

    $scope.setProject =function(newProject){
        $scope.project = newProject;
        var cards = newProject.cards;
        for (index = 0; index < cards.length; ++index) {
            if (cards[index].id == $scope.card.id){
                $scope.card = cards[index];
            }
        }
    };
}
]);

angular.module('fixit').controller('ItemCardController', ['ProjectService', '$scope', function (ProjectService, $scope) {

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

angular.module('fixit').controller('EditCardController', ['ProjectService',
                                                            '$scope',
                                                            '$state',
                                                            '$ionicPopup',
                                                            'project',
                                                            'card',
                                                            function (ProjectService, $scope, $state, $ionicPopup, project, card) {

    $scope.project = project;
    $scope.card = card;


    $scope.addCard = function(projectId, card) {
        console.log("Add a card to project " + projectId);
        ProjectService.addCard(projectId, card).then(function (data) {
            $state.go('app.project-edit', {projectId: project.id});
        });
    };

    // A confirm dialog
    $scope.deleteCard = function(projectId, card) {
        var confirmPopup = $ionicPopup.confirm({
            title: 'Delete Card',
            template: 'Are you sure you want to delete this card?'
        });
        confirmPopup.then(function(res) {
            if(res) {
                console.log("Delete a card from project " + projectId);
                ProjectService.deleteCard(projectId, card).then(function (data) {
                    $state.go('app.project-edit', {projectId: project.id});
                });
            }
        });
    };


}
]);