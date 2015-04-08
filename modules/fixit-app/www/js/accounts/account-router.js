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
		.state('app.sign-out', {
			url: "/signout",
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
		.state('app.user-followers', {
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
		.state('app.user-projects', {
			url: "/user/:username/projects",
			views: {
				'menuContent': {
					templateUrl: "templates/accounts/account-projects.html",
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
		.state('app.user-contributions', {
			url: "/user/:username/projects",
			views: {
				'menuContent': {
					templateUrl: "templates/accounts/account-contributions.html",
					controller: 'UserContributionController',
					resolve: {
						username: function ($stateParams) {
							return $stateParams.username;
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
