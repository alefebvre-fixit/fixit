angular.module('ya-app').config(function ($stateProvider, $urlRouterProvider) {
	$stateProvider
		.state('sign-in', {
			url: "/signin",
			templateUrl: "templates/sign-in.html",
			controller: 'YaController',
			authenticate: false
		})
		.state('sign-out', {
			url: "/signout",
			templateUrl: "templates/sign-in.html",
			controller: 'YaController',
			authenticate: false
		})
		.state('sign-up', {
			url: "/signup",
			templateUrl: "templates/sign-up.html",
			controller: 'SignUpController',
			resolve: {
				signup: function ($stateParams) {
					console.log('sign-up: resolve signup');
					return {};
				}
			},
			authenticate: false
		})
		.state('tabs.settings', {
			url: "/settings",
			views: {
				'tab-account': {
					templateUrl: "templates/settings.html",
					controller: 'EditSettingController',
					resolve: {
						profile: function ($rootScope) {
							console.log('settings: resolve profile');
							return JSON.parse(JSON.stringify($rootScope.user.profile));
						}
					}
				}
			},
			authenticate: true
		})
		.state('user-followers', {
			url: "/user/:username/followers",
			views: {
				'tab-account': {
					templateUrl: "templates/accounts/account-followers.html",
					controller: 'FollowersController',
					resolve: {
						username: function ($stateParams) {
							return $stateParams.username;
						}
					}
				}
			},
			authenticate: true
		})
		.state('user-groups', {
			url: "/user/:username/groups",
			views: {
				'tab-account': {
					templateUrl: "templates/accounts/account-groups.html",
					controller: 'UserProjectController',
					resolve: {
						username: function ($stateParams) {
							return $stateParams.username;
						}
					}
				}
			},
			authenticate: true
		})
		.state('user', {
			url: "/user/:username",
			templateUrl: "templates/accounts/account-summary.html",
			controller: 'UserController',
			resolve: {
				username: function ($stateParams) {
					return $stateParams.username;
				}
			},
			authenticate: true
		});

});
