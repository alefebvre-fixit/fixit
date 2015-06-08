angular.module('fixit').controller('TesterController', ['$scope', '$rootScope', '$cordovaToast', '$cordovaDatePicker','$cordovaCamera', '$http',
	function ($scope, $rootScope, $cordovaToast, $cordovaDatePicker, $cordovaCamera, $http) {

		$scope.map = { center: { latitude: 45, longitude: -73 }, zoom: 8 };

		$scope.callToast = function() {
			console.log('Calling callToast');
			$cordovaToast.show('Hello', 'long', 'center').then(
				function(success) {
					// success
				}, function(error) {
					// error
				});
		};

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
		};

		$scope.inAppBrowser = function() {

		};





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


			$scope.urlForImage = function () {
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

//This is a test for https://angular-ui.github.io/angular-google-maps/#
angular.module('fixit').controller('MapController', ['$scope', '$cordovaGeolocation', '$ionicSideMenuDelegate',
	function ($scope, $cordovaGeolocation, $ionicSideMenuDelegate) {

        $ionicSideMenuDelegate.canDragContent(false);
        //map variable containing the map details, will be referenced from the html
        $scope.map = {center: {latitude: 51.219053, longitude: 4.404418 }, zoom: 14 };
        //map options
        $scope.options = {scrollwheel: false};
        $scope.markers = [];

        $scope.centerOnMe = function(){

            // get position of user and then set the center of the map to that position
            $cordovaGeolocation
                .getCurrentPosition()
                .then(function (position) {
                    var lat  = position.coords.latitude
                    var long = position.coords.longitude
                    $scope.map = {center: {latitude: lat, longitude: long}, zoom: 16 };
                    //just want to create this loop to make more markers
                    for(var i=0; i<3; i++) {
                        $scope.markers.push({
                            id: $scope.markers.length,
                            latitude: lat + (i * 0.002),
                            longitude: long + (i * 0.002),
                            title: 'm' + i
                        })
                    }

                }, function(err) {
                    // error
                });



        };



    }
]);

