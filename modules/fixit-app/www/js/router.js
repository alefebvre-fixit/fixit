angular.module('fixit').config(function ($stateProvider, $urlRouterProvider) {
	$stateProvider
		.state('app', {
			url: "/app",
			abstract: true,
			templateUrl: "templates/menu.html",
			controller: 'FixItController'
		}).state('app.projects', {
			url: "/projects",
			views: {
				'menuContent': {
					templateUrl: "templates/projects.html",
					controller: 'MyProjectController',
					resolve: {
						projects: function ($rootScope, ProjectService) {
							var username = localStorage.getItem("username");
							console.log('Resolve Projects' + username);
							if (username) {
								return ProjectService.getProjectsByOwner(username);
							} else {
								return {};
							}
						}
					}
				}
			},
			authenticate: true
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
		.state('app.card-selector', {
			url: "/projects/:projectId/selector",
			views: {
				'menuContent': {
					templateUrl: "templates/cards/card-selector.html",
					controller: 'CardSelectorController',
					resolve: {
						project: function ($stateParams, ProjectService) {
							console.log('card-selector: resolve single project');
							return ProjectService.getProject($stateParams.projectId);
						}
					}
				}
			},
			authenticate: true
		})
		.state('app.card-new', {
			cache: false,
			url: "/projects/:projectId/cards/new/:type",
			views: {
				'menuContent': {
					templateUrl: "templates/cards/card-edit-list.html",
					controller: 'EditCardController',
					resolve: {
						project: function ($stateParams, ProjectService) {
							console.log('card-new: resolve single project');
							return ProjectService.getProject($stateParams.projectId);
						},
						card: function($stateParams, ProjectService){
							console.log('card-new: resolve single card');
							return ProjectService.instanciateCard($stateParams.projectId, $stateParams.type);
						}
					}
				}
			},
			authenticate: true
		})
		.state('app.card-edit', {
			url: "/projects/:projectId/cards/:cardId/edit",
			cache: false,
			views: {
				'menuContent': {
					templateUrl: "templates/cards/card-edit-list.html",
					controller: 'EditCardController',
					resolve: {
						project: function ($stateParams, ProjectService) {
							return ProjectService.getProject($stateParams.projectId);
						},
						card: function($stateParams, ProjectService){
							return ProjectService.getCard($stateParams.projectId, $stateParams.cardId);
						}
					}
				}
			},
			authenticate: true
		})
		.state('app.card-view', {
			cache: false,
			url: "/projects/:projectId/cards/:cardId",
			views: {
				'menuContent': {
					templateUrl: "templates/cards/card-detail.html",
					controller: 'CardController',
					resolve: {
						project: function ($stateParams, ProjectService) {
							console.log('app.card-view: resolve project');
							return ProjectService.getProject($stateParams.projectId);
						},
						summary: function($stateParams, CardService){
							console.log('app.card-view: resolve summary');
							return CardService.getCardSummary($stateParams.projectId, $stateParams.cardId);
						}

					}
				}
			},
			authenticate: true

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

		.state('app.project-single', {
			cache: false,
			url: "/projects/:projectId",
			views: {
				'menuContent': {
					templateUrl: "templates/project-view.html",
					controller: 'ViewProjectController',
					resolve: {
						project: function ($stateParams, ProjectService) {
							console.log('resolve project-single id=' + $stateParams.projectId);
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
});
