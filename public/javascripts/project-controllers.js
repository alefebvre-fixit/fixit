
fixItApp.config(['$routeProvider', '$locationProvider',
    function($routeProvider, $locationProvider) {
        $routeProvider.
            when('/item', {
                templateUrl: '/assets/partials/item-card-input.html',
                controller: 'EditProjectController'
            }).
            when('/date', {
                templateUrl: '/assets/partials/date-card-input.html',
                controller: 'EditProjectController'
            }).
            when('/account', {
                templateUrl: '/assets/partials/settings-account.html',
                controller: 'EditAccountController'
            }).
            when('/profile', {
                templateUrl: '/assets/partials/settings-profile.html',
                controller: 'EditAccountController'
            }).
            otherwise({
                redirectTo: '/'
            });
    }]);


fixItApp.factory('ProjectService', ['$http', function($http) {
	
	  var resultService = {
			  getProjects: function() {
			      var promise = $http.get('/api/projects').then(function (response) {
			    	  return response.data;
			      });
			      return promise;
			    },
			    getProjectsByOwner: function(username) {
				      var promise = $http.get('/api/user/'+ username +'/projects').then(function (response) {
				        return response.data;
				      });
				      return promise;
			  	},
                getProject: function(projectId) {
                      var promise = $http.get('/api/projects/'+ projectId).then(function (response) {
                          return response.data;
                      });
                      return promise;
                },
			    save: function(project) {
				      var promise = $http.post('/api/projects', project).then(function (response) {
				        return response.data;
				      });
				      return promise;
			  	},
                delete: function(project) {
                      var promise = $http.post('/api/projects/' + project.id + '/delete').then(function (response) {
                          return response.data;
                      });
                      return promise;
                },
              provide: function(project, card, quantity) {
                  var promise = $http.post('/api/projects/' + project.id + '/cards/' + card.id  +'/provide?quantity=' + quantity).then(function (response) {
                      return response.data;
                  });
                  return promise;
              },
              cancelContribution: function(project, contribution) {
                  var promise = $http.post('/api/projects/' + project.id + '/contributions/' + contribution.id  +'/cancel').then(function (response) {
                      return response.data;
                  });
                  return promise;
              },
			  instanciateProject:function(){
			  		var result = {"name":"Project A","description":"AAA","city":"Paris","country":"France","cards":[{"type":"item","name":"my item", "required":"1"}]};
			  		return result;
			  	}
			  
			  };
	
	  return resultService;
	}]);


fixItApp.controller('FixItController', ['$scope', '$http', function ($scope, $http) {
    $scope.currentuser;
    $scope.init = function(username)
    {
        $scope.currentuser = username;
    };


    $scope.saveAccount = function() {
        SettingService.saveAccount()
    }

}
]);

fixItApp.controller('ProjectController', ['ProjectService', '$scope', '$http', function (ProjectService, $scope, $http) {

    $scope.init = function(username)
	{
	    //This function is sort of private constructor for controller
        if(username){
            ProjectService.getProjectsByOwner(username).then(function (data) {
                $scope.projects = data;
            });
        } else {
            ProjectService.getProjects().then(function (data) {
                $scope.projects = data;
            });
        }
	};
	


    }
]);



fixItApp.controller('ViewProjectController', ['ProjectService', '$scope', '$http', function (ProjectService, $scope, $http) {

    $scope.init = function(projectId)
    {
        ProjectService.getProject(projectId).then(function (data) {
            $scope.project = data;
        });

    };

    $scope.setProject =function(newProject){
        $scope.project = newProject;
    };
}
]);

fixItApp.controller('EditProjectController', ['ProjectService', '$scope', '$http', '$modal', function (ProjectService, $scope, $http, $modal) {

    $scope.status = {
        isopen: false
    };

    $scope.toggled = function(open) {
        console.log('Dropdown is now: ', open);
    };

    $scope.toggleDropdown = function($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.status.isopen = !$scope.status.isopen;
    };

    $scope.init = function(projectId)
	{
        if ((!projectId || 0 === projectId.length)){
            console.log('Init without projectId');
            $scope.project = ProjectService.instanciateProject();
        } else {
            console.log('Init with projectId=' + projectId);
            ProjectService.getProject(projectId).then(function (data) {
                $scope.project = data;
            });
        }
	};
	
    $scope.saveProject = function() {
		ProjectService.save($scope.project).then(function (data) {
		    $scope.project = data;
         });
    }

    $scope.removeCard = function(index) {
    	$scope.project.cards.splice(index, 1);
    }

    $scope.removeProject = function (size) {

        var modalInstance = $modal.open({
            templateUrl: 'myModalContent.html',
            controller: ModalInstanceCtrl,
            size: 'sm'

        });

        modalInstance.result.then(function () {
            ProjectService.delete($scope.project).then(function (data) {
                $scope.project = ProjectService.instanciateProject();
            });
        }, function () {

        });
    };
    	
    }
]);





fixItApp.controller('EditItemCardController', ['ProjectService', '$scope', '$http', function (ProjectService, $scope, $http) {

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


fixItApp.controller('EditDateCardController', ['ProjectService', '$scope', '$http', function (ProjectService, $scope, $http) {

    $scope.dateCard = {type : 'date'};

    $scope.addCard = function() {
        $scope.project.cards.push($scope.dateCard);
    }



}
]);




// Please note that $modalInstance represents a modal window (instance) dependency.
// It is not the same as the $modal service used above.

var ModalInstanceCtrl = function ($scope, $modalInstance) {

    $scope.ok = function () {
        $modalInstance.close();
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
};


