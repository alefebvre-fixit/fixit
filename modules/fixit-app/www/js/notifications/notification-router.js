angular.module('fixit').config(function ($stateProvider, $urlRouterProvider) {
	$stateProvider
		.state('app.notifications', {
			url: "/notifications",
			views: {
				'menuContent': {
					templateUrl: "templates/notifications/notification-list.html",
					controller: 'NotificationListController',
					resolve: {
						notifications: function ($stateParams, NotificationService) {
							console.log('notifications: resolve notifications');
							return NotificationService.getNotifications();
						}
					}
				}
			},
			authenticate: false
		});
});
