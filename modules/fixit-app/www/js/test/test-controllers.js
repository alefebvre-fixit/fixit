angular.module('fixit').controller('TesterController', ['$scope', '$rootScope', '$cordovaToast', '$cordovaDatePicker',
	function ($scope, $rootScope, $cordovaToast, $cordovaDatePicker) {

		$scope.callToast = function() {
			console.log('Calling callToast');
			$cordovaToast.show('Hello', 'long', 'center').then(
				function(success) {
					// success
				}, function(error) {
					// error
				});
		}

		$scope.callDatePicker = function() {
			console.log('Calling callDatePicker');

			var options = {
				date: new Date(),
				mode: 'date', // or 'time'
				minDate: new Date() - 10000,
				allowOldDates: true,
				allowFutureDates: false,
				doneButtonLabel: 'DONE',
				doneButtonColor: '#F2F3F4',
				cancelButtonLabel: 'CANCEL',
				cancelButtonColor: '#000000'
			};

			document.addEventListener("deviceready", function () {

				$cordovaDatePicker.show(options).then(function(date){
					alert(date);
				});

			}, false);
		}






		$scope.data = {
			showDelete: false
		};

		$scope.edit = function(item) {
			alert('Edit Item: ' + item.id);
		};
		$scope.share = function(item) {
			alert('Share Item: ' + item.id);
		};

		$scope.moveItem = function(item, fromIndex, toIndex) {
			$scope.items.splice(fromIndex, 1);
			$scope.items.splice(toIndex, 0, item);
		};

		$scope.onItemDelete = function(item) {
			$scope.items.splice($scope.items.indexOf(item), 1);
		};

		$scope.items = [
			{ id: 0 },
			{ id: 1 },
			{ id: 2 },
			{ id: 3 }
		];


	}
]);



