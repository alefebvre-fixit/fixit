angular.module('fixit').config(function ($stateProvider, $urlRouterProvider) {
	$stateProvider
		.state('app.projects', {
			url: "/projects",
			views: {
				'menuContent': {
					templateUrl: "templates/projects/project-list.html",
					controller: 'MyProjectController'
				}
			},
			authenticate: true
		})
		.state('app.project-new', {
			url: "/projects/new",
			views: {
				'menuContent': {
					templateUrl: "templates/projects/project-new.html",
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
		.state('app.project-single', {
			cache: false,
			url: "/projects/:projectId",
			views: {
				'menuContent': {
					templateUrl: "templates/projects/project-view.html",
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
		.state('app.project-edit', {
			cache: false,
			url: "/projects/:projectId/edit",
			views: {
				'menuContent': {
					templateUrl: "templates/projects/project-edit.html",
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
					templateUrl: "templates/projects/discover.html",
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
		.state('app.project-comments', {
			cache: false,
			url: "/projects/:projectId/comments",
			views: {
				'menuContent': {
					templateUrl: "templates/projects/project-comments.html",
					controller: 'ProjectCommentsController',
					resolve: {
						projectId: function ($stateParams) {
							return $stateParams.projectId;
						}
					}
				}
			},
			authenticate: true
		})
		.state('app.project-followers', {
			cache: false,
			url: "/projects/:projectId/followers",
			views: {
				'menuContent': {
					templateUrl: "templates/projects/project-followers.html",
					controller: 'ProjectFollowersController',
					resolve: {
						projectId: function ($stateParams) {
							return $stateParams.projectId;
						}
					}
				}
			},
			authenticate: true
		})
		.state('app.project-contributions', {
			cache: false,
			url: "/projects/:projectId/contributions",
			views: {
				'menuContent': {
					templateUrl: "templates/projects/project-contributions.html",
					controller: 'ProjectContributionsController',
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
