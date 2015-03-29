angular.module('fixit').config(function ($stateProvider, $urlRouterProvider) {
	$stateProvider
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
						card: function($stateParams, CardService){
							console.log('card-new: resolve single card');
							return CardService.instanciateCard($stateParams.projectId, $stateParams.type);
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
						projectId: function ($stateParams) {
							return $stateParams.projectId;
						},
						cardId: function($stateParams){
							return $stateParams.cardId;
						}

					}
				}
			},
			authenticate: true

		})
		;

});
