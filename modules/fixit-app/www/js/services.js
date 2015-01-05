angular.module('fixit').factory('ProjectService', ['$http', '$rootScope', function($http, $rootScope) {

	  var resultService = {
			  getProjects: function() {
				  var promise = $http.get($rootScope.baseUrl + '/api/projects').then(function (response) {
				        return response.data;
				  });
				  return promise;
			    },
			    getProjectsByOwner: function(username) {
				      var promise = $http.get($rootScope.baseUrl + '/api/user/'+ username +'/projects').then(function (response) {
				        return response.data;
				      });
				      return promise;
			  	},
			  	
                getProject: function(projectId) {
                      var promise = $http.get($rootScope.baseUrl + '/api/projects/'+ projectId).then(function (response) {
                          return response.data;
                      });
                      return promise;
                },
			    saveProject: function(project) {
				      var promise = $http.post($rootScope.baseUrl + '/api/projects', project).then(function (response) {
				        return response.data;
				      });
				      return promise;
			  	},
                  publishProject: function(project) {
                      var promise = $http.post($rootScope.baseUrl + '/api/projects/' + project.id + '/publish').then(function (response) {
                          return response.data;
                      });
                      return promise;
                  },
                deleteProject: function(project) {
                      var promise = $http.post($rootScope.baseUrl + '/api/projects/' + project.id + '/delete').then(function (response) {
                          return response.data;
                      });
                      return promise;
                },
              getCard: function(projectId, cardId) {
                  var promise = $http.get($rootScope.baseUrl + '/api/projects/'+ projectId + '/cards/' + cardId).then(function (response) {
                      return response.data;
                  });
                  return promise;
              },
              addCard: function(projectId, card) {
                  var promise = $http.post($rootScope.baseUrl + '/api/projects/'+ projectId + '/cards', card).then(function (response) {
                      return response.data;
                  });
                  return promise;
              },
              updateCard: function(projectId, card) {
                  var promise = $http.put($rootScope.baseUrl + '/api/projects/'+ projectId + '/cards/' + card.id , card).then(function (response) {
                      return response.data;
                  });
                  return promise;
              },
              deleteCard: function(projectId, card) {
                  var promise = $http.delete($rootScope.baseUrl + '/api/projects/'+ projectId + '/cards/' + card.id).then(function (response) {
                      return response.data;
                  });
                  return promise;
              },
              provide: function(project, card, quantity) {
                  var promise = $http.post($rootScope.baseUrl + '/api/projects/' + project.id + '/cards/' + card.id  +'/provide?quantity=' + quantity).then(function (response) {
                      return response.data;
                  });
                  return promise;
              },
              cancelContribution: function(project, contribution) {
                  var promise = $http.post($rootScope.baseUrl + '/api/projects/' + project.id + '/contributions/' + contribution.id  +'/cancel').then(function (response) {
                      return response.data;
                  });
                  return promise;
              },
			  instanciateProject:function(){
                  var promise = $http.get($rootScope.baseUrl + '/api/projects/new').then(function (response) {
                      return response.data;
                  });
                  return promise;
			  },
              instanciateCard:function(project, type){
                  var promise = $http.post($rootScope.baseUrl + '/api/projects/' + project.id + '/cards/new/' + type).then(function (response) {
                      return response.data;
                  });
                  return promise;
              }
			  
			  };
	
	  return resultService;
	}]);


angular.module('fixit').factory('SettingService', ['$http', '$rootScope', function($http, $rootScope) {
	
    var resultService = {
        getAccount: function() {
            var promise = $http.get($rootScope.baseUrl + '/api/account').then(function (response) {
                return response.data;
            });
            return promise;
        },
        getAccountSummary: function() {
            var promise = $http.get($rootScope.baseUrl + '/api/account/summary').success(function (response) {
                return response.data;
            });
            return promise;
        },
        saveAccount: function(account) {
            var promise = $http.post($rootScope.baseUrl + '/api/account', account).then(function (response) {
                return response.data;
            });
            return promise;
        },
        signupUser: function(signupRequest) {
            console.log("Posting signupUser" + signupRequest.email);

            var promise = $http.post($rootScope.baseUrl + '/api/signup', signupRequest).then(function (response) {
                return response.data;
            });
            return promise;
        },
        signinUser: function(signinRequest) {
            console.log("BBPosting signinUser:" + $rootScope.baseUrl + '/api/signin'+ signinRequest.username + '/' + signinRequest.password);
            return $http.post($rootScope.baseUrl + '/api/signin', signinRequest);
        }
    };

    return resultService;
}]);


