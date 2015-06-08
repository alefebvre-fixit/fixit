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
		})

		.state('app.work1', {
			url: "/work1",
			views: {
				'menuContent': {
					templateUrl: "templates/test/work-1.html"
				}

			},
			authenticate: false
		})

		.state('app.work2', {
			url: "/work2",
			views: {
				'menuContent': {
					templateUrl: "templates/test/work-2.html"
				}

			},
			authenticate: false
		})
		.state('app.work3', {
			url: "/work3",
			views: {
				'menuContent': {
					templateUrl: "templates/test/work-3.html",
					controller: 'MapController'
				}

			},
			authenticate: false
		})
	;
});
