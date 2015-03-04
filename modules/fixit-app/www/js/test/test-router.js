angular.module('fixit').config(function ($stateProvider, $urlRouterProvider) {
	$stateProvider
		.state('app.tester', {
			url: "/tester",
			views: {
				'menuContent': {
					templateUrl: "templates/test/tester.html",
					controller: 'TesterController'
				}
			},
			authenticate: false
		});
});
