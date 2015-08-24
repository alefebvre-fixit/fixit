angular.module('ya-app').config(function ($stateProvider, $urlRouterProvider) {
	$stateProvider
		// Each tab has its own nav history stack:
		.state('tabs', {
			url: "/tabs",
			abstract: true,
			templateUrl: "templates/tabs.html"
		})
		.state('test', {
			url: "/test",
			templateUrl: "templates/test.html",
			authenticate: false
		})
		.state('tabs.activity', {
			url: '/activity',
			views: {
				'tab-activity': {
					templateUrl: 'templates/tab-activity.html',
					controller: 'DashCtrl'
				}
			},
			authenticate: false
		})

		.state('tabs.account', {
			url: '/account',
			views: {
				'tab-account': {
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
