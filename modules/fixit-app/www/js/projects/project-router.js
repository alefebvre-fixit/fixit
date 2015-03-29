angular.module('fixit').config(function ($stateProvider, $urlRouterProvider) {
	$stateProvider
		.state('app.projects', {
			url: "/projects",
			views: {
				'menuContent': {
					templateUrl: "templates/projects.html",
					controller: 'MyProjectController'
				}
			},
			authenticate: true
		})
		.state('app.project-single', {
			cache: false,
			url: "/projects/:projectId",
			views: {
				'menuContent': {
					templateUrl: "templates/project-view.html",
					controller: 'ViewProjectController',
					resolve: {
						projectId: function ($stateParams) {
							return $stateParams.projectId;
						}
					}
				}
			},
			authenticate: true

		})
		;

});
