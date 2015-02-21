angular.module('fixit').config(function ($stateProvider, $urlRouterProvider) {
	$stateProvider
		.state('app.sign-in', {
			url: "/signin",
			views: {
				'menuContent': {
					templateUrl: "templates/sign-in.html"
				}
			},
			authenticate: false
		})
		.state('app.sign-up', {
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
		.state('app.settings', {
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
		.state('app.followers', {
			url: "/user/:username/followers",
			views: {
				'menuContent': {
					templateUrl: "templates/followers.html",
					controller: 'FollowersController',
					resolve: {
						followers: function ($stateParams, SettingService) {
							return SettingService.getFollowers($stateParams.username);
						}
					}
				}
			},
			authenticate: true
		})
	.state('app.user', {
		url: "/user/:username",
		views: {
			'menuContent': {
				templateUrl: "templates/user.html",
				controller: 'UserController',
				resolve: {
					summary: function (SettingService, $stateParams) {
						console.log('app.user: resolve profile');
						return SettingService.getUserSummary($stateParams.username);
					}
				}
			}
		},
		authenticate: true
	});

});
