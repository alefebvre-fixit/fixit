angular.module('fixit').controller('SignUpController', ['SettingService', '$scope', '$http', '$location', function (SettingService, $scope, $http, $location) {

    $scope.submitForm = function (isValid) {
        // check to make sure the form is completely valid
        if (isValid) {
            console.log("SignupForm is valid" + $scope.signupRequest.email);
            SettingService.signupUser($scope.signupRequest).then(function (data) {
                console.log("Just signed up");
                $location.url('projects');
            });
        } else {
            console.log("SignupForm is not valid");
        }
    };

}
]);

angular.module('fixit').controller('SignInController', ['SettingService', '$scope', '$state', '$rootScope', function (SettingService, $scope, $state, $rootScope) {

    $scope.signin = {username: 'antoinelefebvre', password: 'password'};
    // Perform the login action when the user submits the login for
    $scope.doSignIn = function () {

        console.log('Doing login' + $scope.signin.username + '/' + $scope.signin.password);

        SettingService.signinUser($scope.signin).success(function (loggedUser) {
            console.log("Your name is: " + loggedUser.username);
            $rootScope.user = loggedUser;

            localStorage.setItem("username", $rootScope.user.username);
            console.log("localStorage name is: " + localStorage.getItem("username"));
            $state.transitionTo('app.projects');


        }).error(function (response, status) {
            console.log("Invalid username or password");
            $scope.signin.error = 'Invalid username or password';
        });

    };


}
]);




