// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js

angular.module('fixit', [ 'ionic', 'ngCordova']);


angular.module('fixit').constant('fixitSettings', {
	apiRrl: 'http://localhost:9000'
});


angular.module('fixit').run(
		function($ionicPlatform, $rootScope, $state) {
		    $ionicPlatform
			    .ready(function() {

				var localFixitURL = 'http://localhost:9000';
				var herokuFixitURL = 'http://intense-meadow-9097.herokuapp.com';
				var herokuFixitURL2 = 'http://vast-gorge-2883.herokuapp.com';

				$rootScope.user = {};
				$rootScope.baseUrl = localFixitURL;

				// Hide the accessory bar by default (remove
				// this to show the accessory
				// bar above the keyboard
				// for form inputs)
				if (window.cordova
					&& window.cordova.plugins.Keyboard) {
				    cordova.plugins.Keyboard
					    .hideKeyboardAccessoryBar(true);
				}
				if (window.StatusBar) {
				    // org.apache.cordova.statusbar required
				    StatusBar.styleDefault();
				}
			    });

		    $rootScope.$on("$stateChangeStart", function(event,
			    toState, toParams, fromState, fromParams) {
			console.log("Check Authentication authenticate="
				+ toState.authenticate);

			if (toState.authenticate) {
			    if (!$rootScope.user || !$rootScope.user.username){
				$state.go("app.signin");
				event.preventDefault();
			    }
			}
		    });
		})
