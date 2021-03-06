angular.module('ya-app').controller('NotificationListController',
	['YaService', 'NotificationService', '$scope', '$log','$rootScope','$state',
		function (YaService, NotificationService, $scope, $log, $rootScope, $state) {

			$scope.$on('$ionicView.beforeEnter', function (event, viewData) {
				NotificationService.getNotifications().then(function (data) {
					setNotification(data);
				});
				$log.debug("NotificationListController beforeEnter is called");
			});


			$scope.doRefresh = function() {
				NotificationService.getNotifications().then(function (data) {
					$log.debug(data);
					setNotification(data);
					$scope.$broadcast('scroll.refreshComplete');
				});
				//Stop the ion-refresher from spinning
			};

			setNotification = function(notifications){
				$scope.notifications = notifications;
				if (notifications){
					$rootScope.badgecount = Object.keys($scope.notifications).length-1;;
				} else {
					$rootScope.badgecount = 0;
				}
			};


			$scope.acknowledge = function(notification){
				NotificationService.acknowledgeNotification(notification).then(function (data) {
					$scope.notifications.splice($scope.notifications.indexOf(notification), 1);
					$rootScope.badgecount = Object.keys($scope.notifications).length-1;
				});
			};

			$scope.acknowledgeAll = function(){
				YaService.startLoading();
				NotificationService.acknowledgeNotifications().then(function (data) {
					$scope.notifications = [];
					$rootScope.badgecount = Object.keys($scope.notifications).length-1;;
					YaService.stopLoading();
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



