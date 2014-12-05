fixItApp.factory('ProjectService', ['$http', function($http) {
	  var localFixitURL = 'http://fixitserver:9000';
	  var herokuFixitURL = 'http://intense-meadow-9097.herokuapp.com';
	  
	  var baseUrl = herokuFixitURL;
	  
	  var resultService = {
			  getProjects: function() {
				  var promise = $http.get(baseUrl + '/api/projects').then(function (response) {
				        return response.data;
				  });
				  return promise;
			    },
			    getProjectsByOwner: function(username) {
				      var promise = $http.get(baseUrl + '/api/user/'+ username +'/projects').then(function (response) {
				        return response.data;
				      });
				      return promise;
			  	},
			  	
                getProject: function(projectId) {
                      var promise = $http.get(baseUrl + '/api/projects/'+ projectId).then(function (response) {
                          return response.data;
                      });
                      return promise;
                },
			    save: function(project) {
				      var promise = $http.post(baseUrl + '/api/projects', project).then(function (response) {
				        return response.data;
				      });
				      return promise;
			  	},
                deleteProject: function(project) {
                      var promise = $http.post(baseUrl + '/api/projects/' + project.id + '/delete').then(function (response) {
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





