angular.module('ya-app').config(function ($stateProvider, $urlRouterProvider) {
	$stateProvider
		// Each tab has its own nav history stack:
		.state('tabs', {
			url: "/tabs",
			abstract: true,
			controller: 'YaController',
			templateUrl: "templates/tabs.html"
		})
		.state('test', {
			url: "/test",
			templateUrl: "templates/test-2.html",
			authenticate: false
		})
		;



	// if none of the above states are matched, use this as the fallback
	$urlRouterProvider.otherwise('/signin');
	//$urlRouterProvider.otherwise('/test');

});
