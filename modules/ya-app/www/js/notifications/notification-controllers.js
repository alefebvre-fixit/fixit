angular.module('ya-app').controller('NotificationListController',
	['NotificationService', '$scope','$rootScope','$state',
		function (NotificationService, $scope, $rootScope, $state) {

			NotificationService.getNotifications().then(function (data) {
				$scope.notifications = data;
				if (data){
					$rootScope.badgecount = Object.keys(data).length;
				} else {
					$rootScope.badgecount = 0;
				}

			});

			$scope.doRefresh = function() {
				NotificationService.getNotifications().then(function (data) {
					$scope.notifications = data;
				});
				//Stop the ion-refresher from spinning
				$scope.$broadcast('scroll.refreshComplete');
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



