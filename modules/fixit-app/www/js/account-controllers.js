angular.module('fixit').controller('SignUpController', ['SettingService', '$scope', '$http', '$state', 'signup',
    function (SettingService, $scope, $http, $state, signup) {

        $scope.signup = signup;


        $scope.doSignUp = function () {

            console.log('$scope.signup' + $scope.signup);


            SettingService.signupUser($scope.signup).success(function (data) {
                $scope.setUser(data);
                $state.transitionTo('app.projects');
            }).error(function (response, status) {
                console.log("Invalid signup");
                $scope.signup.error = 'Invalid signup';
            });
        };

        $scope.goToSignIn = function () {
            $state.transitionTo('app.sign-in');
        };
    }
]);

angular.module('fixit').controller('SignInController', ['SettingService', '$scope', '$state',
    function (SettingService, $scope, $state) {

        $scope.signin = {username: 'antoinelefebvre', password: 'password'};
        // Perform the login action when the user submits the login for
        $scope.doSignIn = function () {

            SettingService.signinUser($scope.signin).success(function (loggedUser) {
                $scope.setUser(loggedUser);

                $state.transitionTo('app.projects');

            }).error(function (response, status) {
                console.log("Invalid username or password");
                $scope.signin.error = 'Invalid username or password';
            });

        };

        $scope.goToSignUp = function () {
            $state.transitionTo('app.sign-up');
        };


    }
]);



angular.module('fixit').controller('EditSettingController', ['SettingService', '$scope', 'profile', '$cordovaToast',
    function (SettingService, $scope, profile, $cordovaToast) {

        $scope.profile = profile;

        $scope.saveProfile = function(profile) {
            SettingService.saveProfile(profile).then(function (data) {
                $scope.setUser(data);
                $scope.profile = data.profile;
                $scope.toastMe('Setting Updated');
            });
        };

    }
]);


angular.module('fixit').controller('UserController', ['$scope', 'SettingService', 'summary', '$ionicPopup',
    function ($scope, SettingService, summary, $ionicPopup) {

        $scope.summary = summary;

        $scope.follow = function(username){
            console.log("follow=" + username);
            SettingService.follow(username).then(function (data) {
                $scope.setUser(data);
                SettingService.getUserSummary(username).then(function (data) {
                    $scope.summary = data;
                });
            });
        };

        $scope.unfollow = function(username){
            var confirmPopup = $ionicPopup.confirm({
                title: 'Unfollow',
                template: 'Stop following ' + username + ' ?'
            });
            confirmPopup.then(function(res) {
                if(res) {
                    console.log("unfollow=" + username);
                    SettingService.unfollow(username).then(function (data) {
                        $scope.setUser(data);
                        SettingService.getUserSummary(username).then(function (data) {
                            $scope.summary = data;
                        });
                    });
                }
            });
        };

        $scope.isFollowing = function(summary){
            console.log('Call isFollowing');
            var arrayLength = summary.user.followers.length;
            for (var i = 0; i < arrayLength; i++) {
                if ($scope.user.username == summary.user.followers[i]){
                    return true;
                }
            }
            return false;
        };


    }
]);
