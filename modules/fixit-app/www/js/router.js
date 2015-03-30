angular.module('fixit').config(function ($stateProvider, $urlRouterProvider) {
	$stateProvider
		.state('app', {
			url: "/app",
			abstract: true,
			templateUrl: "templates/menu.html",
			controller: 'FixItController'
		})



		;

		

	// if none of the above states are matched, use this as the fallback
	$urlRouterProvider.otherwise('/app/signin');
	//$urlRouterProvider.otherwise('/app/work3');
});
