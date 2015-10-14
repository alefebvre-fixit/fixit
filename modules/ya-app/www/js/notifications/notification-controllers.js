angular.module('ya-app').controller('NotificationListController',
	['NotificationService', '$scope',
		function (NotificationService, $scope) {

			NotificationService.getNotifications().then(function (data) {
				$scope.notifications = data;
			});

			$scope.doRefresh = function() {
				NotificationService.getNotifications().then(function (data) {
					$scope.notifications = data;
				});
				//Stop the ion-refresher from spinning
				$scope.$broadcast('scroll.refreshComplete');
			};

		}
	]);



