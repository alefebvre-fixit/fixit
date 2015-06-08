// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js

angular.module('fixit', [ 'ionic', 'ngCordova', 'angularMoment', 'chart.js', 'uiGmapgoogle-maps']);


angular.module('fixit').constant('fixitSettings', {
	apiRrl: 'http://localhost:9000'
});


angular.module('fixit').config(
	function(uiGmapGoogleMapApiProvider) {
	uiGmapGoogleMapApiProvider.configure({
		key: '55883713895-e9egmn26h1ilo7n8msj9ptsfs48dagp3.apps.googleusercontent.com',
		v: '3.17',
		libraries: '',
		language: 'en',
		sensor: 'false',
	})});



angular.module('fixit').run(
		function($ionicPlatform, $rootScope, $state) {
		    $ionicPlatform
			    .ready(function() {

				var localFixitURL = 'http://localhost:9000';
				var emulatorFixitURL = 'http://10.0.2.2:9000';
				var herokuFixitURL = 'http://vast-gorge-2883.herokuapp.com';

				$rootScope.user = {};
				$rootScope.baseUrl = herokuFixitURL;
				$rootScope.isPluginEnabled = true;

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
