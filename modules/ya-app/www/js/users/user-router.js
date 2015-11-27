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
					return {};
				}
			},
			authenticate: false
		})
		.state('user-followers', {
			url: "/user/:username/followers",
			templateUrl: "templates/users/user-followers.html",
			controller: 'FollowersController',
			resolve: {
				username: function ($stateParams) {
					return $stateParams.username;
				}
			},
			authenticate: true
		})
		.state('user-groups', {
			url: "/user/:username/groups",
			templateUrl: "templates/users/user-groups.html",
			controller: 'UserProjectController',
			resolve: {
				username: function ($stateParams) {
					return $stateParams.username;
				}
			},
			authenticate: true
		})
		.state('user-edit', {
			cache: true,
			url: "/user/:username/edit",
			templateUrl: "templates/users/user-edit.html",
			controller: 'EditUserController',
			resolve: {
				profile: function ($rootScope) {
					return JSON.parse(JSON.stringify($rootScope.user.profile));
				}
			},
			authenticate: true
		})
		.state('user', {
			cache: true,
			url: "/user/:username",
			templateUrl: "templates/users/user-summary.html",
			controller: 'UserController',
			resolve: {
				username: function ($stateParams) {
					return $stateParams.username;
				}
			},
			authenticate: true
		})
;

});
