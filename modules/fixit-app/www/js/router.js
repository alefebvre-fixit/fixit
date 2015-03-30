angular.module('fixit').config(function ($stateProvider, $urlRouterProvider) {
	$stateProvider
		.state('app', {
			url: "/app",
			abstract: true,
			templateUrl: "templates/menu.html",
			controller: 'FixItController'
		})
		.state('app.project-new', {
			url: "/projects/new",
			views: {
				'menuContent': {
					templateUrl: "templates/project-new.html",
					controller: 'EditProjectController',
					resolve: {
						project: function ($rootScope, ProjectService) {
							console.log('ProjectService.instanciateProject()' + JSON.stringify(ProjectService.instanciateProject()));
							return ProjectService.instanciateProject();
						}
					}
				}
			},
			authenticate: false
		})
		.state('app.project-edit', {
			cache: false,
			url: "/projects/:projectId/edit",
			views: {
				'menuContent': {
					templateUrl: "templates/project-edit.html",
					controller: 'EditProjectController',
					resolve: {
						project: function ($stateParams, ProjectService) {
							console.log('edit-project: resolve single project');
							return ProjectService.getProject($stateParams.projectId);
						}
					}
				}
			},
			authenticate: true

		})
		.state('app.discover', {
			url: "/discover",
			views: {
				'menuContent': {
					templateUrl: "templates/discover.html",
					controller: 'DiscoverProjectController',
					resolve: {
						projects: function ($rootScope, ProjectService) {
							return ProjectService.getProjects();

						}
					}
				}

			},
			authenticate: true
		})

		;

		

	// if none of the above states are matched, use this as the fallback
	$urlRouterProvider.otherwise('/app/signin');
	//$urlRouterProvider.otherwise('/app/work3');
});
