angular.module('fixit').config(function ($stateProvider, $urlRouterProvider) {
	$stateProvider
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
