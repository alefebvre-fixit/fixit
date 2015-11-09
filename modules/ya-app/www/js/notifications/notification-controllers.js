angular.module('ya-app').controller('NotificationListController',
	['NotificationService', '$scope','$rootScope','$state',
		function (NotificationService, $scope, $rootScope, $state) {


			$scope.$on('$ionicView.beforeEnter', function (event, viewData) {
				NotificationService.getNotifications().then(function (data) {
					console.log("NotificationListController getNotifications is called");
					setNotification(data);
				});
				console.log("NotificationListController beforeEnter is called");
			});


			$scope.doRefresh = function() {
				NotificationService.getNotifications().then(function (data) {
					setNotification(data);
					$scope.$broadcast('scroll.refreshComplete');
				});
				//Stop the ion-refresher from spinning
			};

			setNotification = function(notifications){
				$scope.notifications = notifications;
				if (notifications){
					$rootScope.badgecount = Object.keys(notifications).length;
				} else {
					$rootScope.badgecount = 0;
				}
			};


			$scope.goToUser = function(username){
				$state.go('user', {username: username});
			};

			$scope.goToGroup = function(groupId){
				$state.go('group', {groupId: groupId});
			};

			$scope.goToEvent = function(eventId){
				$state.go('event', {eventId: eventId});
			};

		}
	]);



