



angular.module('fixit').controller('NotificationListController',
	['NotificationService', '$scope', 'notifications',
		function (NotificationService, $scope, notifications) {

			$scope.notifications = notifications;

			$scope.doRefresh = function() {
				NotificationService.getNotifications().then(function (data) {
					$scope.notifications = data;
				});
				//Stop the ion-refresher from spinning
				$scope.$broadcast('scroll.refreshComplete');
			};

		}
	]);



