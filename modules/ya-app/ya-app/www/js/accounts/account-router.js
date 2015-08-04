angular.module('ya-app').config(function ($stateProvider, $urlRouterProvider) {
	$stateProvider
		.state('sign-in', {
			url: "/signin",
			templateUrl: "templates/sign-in.html",
			controller: 'YaController',
			authenticate: false
		})
		.state('ya.sign-out', {
			url: "/signout",
			views: {
				'menuContent': {
					templateUrl: "templates/sign-in.html"
				}
			},
			authenticate: false
		})
		.state('ya.sign-up', {
			url: "/signup",
			views: {
				'menuContent': {
					templateUrl: "templates/sign-up.html",
					controller: 'SignUpController',
					resolve: {
						signup: function ($stateParams) {
							console.log('sign-up: resolve signup');
							return {};
						}
					}
				}
			},
			authenticate: false
		})
		.state('ya.settings', {
			url: "/settings",
			views: {
				'menuContent': {
					templateUrl: "templates/settings.html",
					controller: 'EditSettingController',
					resolve: {
						profile: function ($rootScope) {
							console.log('sign-up: resolve profile');
							return JSON.parse(JSON.stringify($rootScope.user.profile));
						}
					}
				}
			},
			authenticate: true
		})
		.state('ya.user-followers', {
			url: "/user/:username/followers",
			views: {
				'menuContent': {
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
		.state('ya.user-groups', {
			url: "/user/:username/groups",
			views: {
				'menuContent': {
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
		.state('ya.user', {
			url: "/user/:username",
			views: {
				'menuContent': {
					templateUrl: "templates/accounts/account-summary.html",
					controller: 'UserController',
					resolve: {
						username: function ($stateParams) {
							return $stateParams.username;
						}
					}
				}
			},
			authenticate: true
		});

});
