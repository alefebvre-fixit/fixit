angular.module('fixit').controller('SignUpController', ['SettingService', '$scope', '$http', '$state', '$rootScope', 'signup', 
                                                        function (SettingService, $scope, $http, $state, $rootScope, signup) {

    $scope.signup = signup;


    $scope.doSignUp = function () {

        console.log('$scope.signup' + $scope.signup);


        SettingService.signupUser($scope.signup).success(function (data) {
            $rootScope.user = data;
            localStorage.setItem("username", $rootScope.user.username);
            console.log("localStorage name is: " + localStorage.getItem("username"));
            $state.transitionTo('app.projects');
        }).error(function (response, status) {
            console.log("Invalid signup");
            $scope.signup.error = 'Invalid signup';
        });
    };

    $scope.goToSignIn = function () {
        $state.transitionTo('app.sign-in');
    }
}
]);

angular.module('fixit').controller('SignInController', ['SettingService', '$scope', '$state', '$rootScope', 
                                                        function (SettingService, $scope, $state, $rootScope) {

    $scope.signin = {username: 'antoinelefebvre', password: 'password'};
    // Perform the login action when the user submits the login for
    $scope.doSignIn = function () {

        console.log('Doing login' + $scope.signin.username + '/' + $scope.signin.password);

        SettingService.signinUser($scope.signin).success(function (loggedUser) {
            $rootScope.user = loggedUser;

            localStorage.setItem("username", $rootScope.user.username);
            console.log("localStorage name is: " + localStorage.getItem("username"));
            $state.transitionTo('app.projects');


        }).error(function (response, status) {
            console.log("Invalid username or password");
            $scope.signin.error = 'Invalid username or password';
        });

    };

    $scope.goToSignUp = function () {
        $state.transitionTo('app.sign-up');
    }


    }
]);



angular.module('fixit').controller('EditSettingController', ['SettingService', '$scope', '$rootScope', 'profile', '$cordovaToast',
                                                             function (SettingService, $scope, $rootScope, profile, $cordovaToast) {

    $scope.profile = profile;

    $scope.saveProfile = function(profile) {
        SettingService.saveProfile(profile).then(function (data) {
            $rootScope.user = data;
            $scope.profile = data.profile;
            $scope.toastMe('Setting Updated');
        });
    }



}
]);


angular.module('fixit').controller('UserController', ['$scope', 'profile', '$cordovaToast',
                                                             function ($scope, profile, $cordovaToast) {

    $scope.profile = profile;


}
]);
