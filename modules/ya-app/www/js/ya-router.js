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
			templateUrl: "templates/test-event.html",
			authenticate: false
		})
		.state('jsdata', {
			url: "/jsdata",
			templateUrl: "templates/groups/group-list.html",
			controller: 'JSDataController'
		})
		;



	// if none of the above states are matched, use this as the fallback
	//$urlRouterProvider.otherwise('/jsdata');
	$urlRouterProvider.otherwise('/signin');
	//$urlRouterProvider.otherwise('/test');

});
