// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js
angular.module('ya-app', ['ionic', 'ngMessages', 'ngCordova', 'angularMoment', 'ion-sticky'])

.run(function($ionicPlatform, $rootScope, $state) {
  $ionicPlatform.ready(function() {


      var localFixitURL = 'http://localhost:9000';
      var emulatorFixitURL = 'http://10.0.2.2:9000';
      var herokuFixitURL = 'http://vast-gorge-2883.herokuapp.com';

      $rootScope.user = {};
      $rootScope.baseUrl = herokuFixitURL;
      $rootScope.isPluginEnabled = true;

    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);

    }
    if (window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleLightContent();
    }

      $rootScope.$on("$stateChangeStart", function(event,
                                                   toState, toParams, fromState, fromParams) {
          console.log("Check Authentication authenticate="
              + toState.authenticate);

          if (toState.authenticate) {
              if (!$rootScope.user || !$rootScope.user.username){
                  $state.go("ya.signin");
                  event.preventDefault();
              }
          }
      });

  });
}
);


angular.module('ya-app').config(function($logProvider) {
    var production = false;
    if (production) {
        $logProvider.debugEnabled(false);
    }
});

angular.module('ya-app').config(function($ionicConfigProvider) {
    console.log("ionic.Platform.isIOS()" + ionic.Platform.isIOS());
    $ionicConfigProvider.scrolling.jsScrolling(true);
});