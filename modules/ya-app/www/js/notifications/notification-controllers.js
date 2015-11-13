angular.module('ya-app').controller('NotificationListController',
	['NotificationService', '$scope', '$log','$rootScope','$state',
		function (NotificationService, $scope, $log, $rootScope, $state) {


			$scope.$on('$ionicView.beforeEnter', function (event, viewData) {
				NotificationService.getNotifications().then(function (data) {
					$log.log("NotificationListController getNotifications is called");
					setNotification(data);
				});
				$log.log("NotificationListController beforeEnter is called");
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
					$rootScope.badgecount = Object.keys($scope.notifications).length;
				} else {
					$rootScope.badgecount = 0;
				}
			};


			$scope.acknowledge = function(notification){
				NotificationService.acknowledgeNotification(notification).then(function (data) {
					$scope.notifications.splice($scope.notifications.indexOf(notification), 1);
					$rootScope.badgecount = Object.keys($scope.notifications).length;
				});
			};

			$scope.acknowledgeAll = function(){
				NotificationService.acknowledgeNotifications().then(function (data) {
					$scope.notifications = [];
					$rootScope.badgecount = Object.keys($scope.notifications).length;
				});
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



