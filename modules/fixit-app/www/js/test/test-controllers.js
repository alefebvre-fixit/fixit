angular.module('fixit').controller('TesterController', ['$scope', '$rootScope', '$cordovaToast', '$cordovaDatePicker','$cordovaCamera', '$http',
	function ($scope, $rootScope, $cordovaToast, $cordovaDatePicker, $cordovaCamera, $http) {

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


		$scope.images = [];

		$scope.addImage = function() {
			// 2
			var options = {
				destinationType: Camera.DestinationType.FILE_URI,
				sourceType: Camera.PictureSourceType.PHOTOLIBRARY, // Camera.PictureSourceType.PHOTOLIBRARY
				allowEdit: false,
				encodingType: Camera.EncodingType.JPEG,
				popoverOptions: CameraPopoverOptions
			};

			// 3
			$cordovaCamera.getPicture(options).then(function (imageData) {

				// 4
				onImageSuccess(imageData);

				function onImageSuccess(fileURI) {
					createFileEntry(fileURI);
				}

				function createFileEntry(fileURI) {
					window.resolveLocalFileSystemURL(fileURI, copyFile, fail);
				}

				// 5
				function copyFile(fileEntry) {
					var name = fileEntry.fullPath.substr(fileEntry.fullPath.lastIndexOf('/') + 1);
					var newName = makeid() + name;

					window.resolveLocalFileSystemURL(cordova.file.dataDirectory, function (fileSystem2) {
							fileEntry.copyTo(
								fileSystem2,
								newName,
								onCopySuccess,
								fail
							);
						},
						fail);
				}

				// 6
				function onCopySuccess(entry) {
					$scope.$apply(function () {
						$scope.images.push(entry.nativeURL);
					});
				}

				function fail(error) {
					console.log("fail: " + error.code);
				}

				function makeid() {
					var text = "";
					var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

					for (var i = 0; i < 5; i++) {
						text += possible.charAt(Math.floor(Math.random() * possible.length));
					}
					return text;
				}

			}, function (err) {
				console.log(err);
			});


			$scope.urlForImage = function (imageName) {
				console.log("get correct path for image");
			}

		};


		$scope.uploadFile = function(files) {
			var fd = new FormData();
			//Take the first selected file
			fd.append("file", files[0]);

			$http.post('http://localhost:9000/api/profile/picture', fd, {
				withCredentials: true,
				headers: {'Content-Type': undefined },
				transformRequest: angular.identity
			}).then(
				function(success) {
					// success
				}, function(error) {
					// error
				});

		};
	}
]);



