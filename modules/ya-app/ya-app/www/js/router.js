angular.module('ya-app').config(function ($stateProvider, $urlRouterProvider) {
	$stateProvider
		.state('ya', {
			url: "/ya",
			abstract: true,
			templateUrl: "templates/tabs.html",
			controller: 'YaController'

		})

		// Each tab has its own nav history stack:

		.state('ya.activity', {
			url: '/activity',
			views: {
				'ya-activity': {
					templateUrl: 'templates/tab-activity.html',
					controller: 'DashCtrl'
				}
			},
			authenticate: false
		})

		.state('ya.account', {
			url: '/account',
			views: {
				'ya-account': {
					templateUrl: 'templates/accounts/account-summary.html',
					controller: 'AccountCtrl'
				}
			},
			authenticate: false
		})

		;

		

	// if none of the above states are matched, use this as the fallback
	$urlRouterProvider.otherwise('/signin');
	//$urlRouterProvider.otherwise('/ya/signin');
	//$urlRouterProvider.otherwise('/app/signin');
	//$urlRouterProvider.otherwise('/app/work3');
});
