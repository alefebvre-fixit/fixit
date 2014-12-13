

fixItApp.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider
	.state('app', {
		url : "/app",
		abstract : true,
		templateUrl : "templates/menu.html",
	}).state('app.projects', {
		url : "/projects",
		views : {
			'menuContent' : {
				templateUrl : "templates/projects.html",
				controller : 'MyProjectController',
				resolve : {
					projects : function($rootScope, ProjectService) {
						var username = localStorage.getItem("username");
						console.log('Resolve Projects' + username);
						if (username){
							return ProjectService.getProjectsByOwner(username);
						} else{
							return {};
						}
					}
				}
			}
		},
		authenticate: true
	})
	.state('app.newproject', {
		url : "/projects/new",
		views : {
			'menuContent' : {
				templateUrl : "templates/project-new.html",
				controller : 'EditProjectController',
				resolve : {
				    project : function($rootScope, ProjectService) {
					console.log('ProjectService.instanciateProject()' + JSON.stringify(ProjectService.instanciateProject()));
					return ProjectService.instanciateProject();
				    }
				}
			}
		},
		authenticate: false
	})
	.state('app.singleproject', {
		url : "/projects/:projectId",
		views : {
			'menuContent' : {
				templateUrl : "templates/project.html",
				controller : 'ViewProjectController',
				resolve : {
					project : function($stateParams, ProjectService) {
						return ProjectService.getProject($stateParams.projectId);
					}
				}
			}
		},
		authenticate: true

	})	
	.state('app.discover', {
		url : "/discover",
		views : {
			'menuContent' : {
				templateUrl : "templates/discover.html"
			}
		},
		authenticate: true
	})

	.state('app.settings', {
		url : "/settings",
		views : {
			'menuContent' : {
				templateUrl : "templates/settings.html"
			}
		},
		authenticate: true
	})
	.state('app.signin', {
		url : "/signin",
		views : {
			'menuContent' : {
				templateUrl : "templates/signin.html"
			}
		},
		authenticate: false
	});
	// if none of the above states are matched, use this as the fallback
	$urlRouterProvider.otherwise('/app/signin');
});
