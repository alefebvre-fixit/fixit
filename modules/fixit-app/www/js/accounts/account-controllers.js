angular.module('fixit').controller('SignUpController', ['SettingService', '$scope', '$state', 'signup', '$ionicLoading',
    function (SettingService, $scope, $state, signup, $ionicLoading) {

        $scope.signup = signup;

        $scope.doSignUp = function () {

            SettingService.signupUser($scope.signup).success(function (data) {
                $scope.setUser(data);
                $state.transitionTo('app.projects');
            }).error(function (response, status) {
                console.log("Invalid sign-up");
                $scope.signup.error = 'Invalid sign-up';
            });
        };

        $scope.goToSignIn = function () {
            $state.transitionTo('app.sign-in');
        };
    }
]);

angular.module('fixit').controller('SignInController', ['SettingService', '$scope', '$state', '$ionicLoading', '$cordovaOauth',
    function (SettingService, $scope, $state, $ionicLoading, $cordovaOauth) {

        $scope.signin = {username: 'antoinelefebvre', password: 'password'};
        // Perform the login action when the user submits the login for
        $scope.doSignIn = function () {

            $ionicLoading.show({
                template: '<ion-spinner class="spinner-positive"></ion-spinner>'
            });

            SettingService.signinUser($scope.signin).success(function (user) {
                SettingService.getFavorites(user.username).then(function(favorites) {
                        $scope.setFavorites(favorites);
                        $scope.setUser(user);
                        $state.transitionTo('app.projects');
                        $ionicLoading.hide();
                    }
                );
            }).error(function (response, status) {
                $ionicLoading.hide();
                console.log("Invalid username or password");
                $scope.signin.error = 'Invalid username or password';
            });
        };


        $scope.goToSignUp = function () {
            $state.transitionTo('app.sign-up');
        };

        $scope.googleLogin = function() {
            $ionicLoading.show({
                template: '<ion-spinner class="spinner-positive"></ion-spinner>'
            });
            $cordovaOauth.google("55883713895-e9egmn26h1ilo7n8msj9ptsfs48dagp3.apps.googleusercontent.com", ["profile"]).then(function(result) {
                console.log(JSON.stringify(result));
                $scope.signin.error = JSON.stringify(result);


                SettingService.signInGoogle(result).success(function (user) {
                    $ionicLoading.hide();

                }).error(function (response, status) {
                    $ionicLoading.hide();
                    console.log("Invalid username or password");
                    $scope.signin.error = 'Invalid username or password';
                });


                $ionicLoading.hide();
            }, function(error) {
                console.log(error);
                $ionicLoading.hide();
                $scope.signin.error = error;
            });
        };

        $scope.facebookLogin = function() {
            $ionicLoading.show({
                template: '<ion-spinner class="spinner-positive"></ion-spinner>'
            });
            $cordovaOauth.facebook("CLIENT_ID_HERE", ["email"]).then(function(result) {
                // results
            }, function(error) {
                $ionicLoading.hide();
                $scope.signin.error = 'Invalid username or password';
                // error
            });
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


angular.module('fixit').controller('UserController', ['$scope', 'SettingService', 'username', '$ionicPopup',
    function ($scope, SettingService, username, $ionicPopup) {

        SettingService.getUserSummary(username).then(function (summary) {
            $scope.summary = summary;
        });

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
            if (summary){
                var arrayLength = summary.user.followers.length;
                for (var i = 0; i < arrayLength; i++) {
                    if ($scope.user.username == summary.user.followers[i]){
                        return true;
                    }
                }
            }
            return false;
        };


    }
]);


angular.module('fixit').controller('FollowersController', ['SettingService', '$scope', 'username',
    function (SettingService, $scope, username) {

        SettingService.getFollowers(username).then(function (followers) {
            $scope.followers = followers;
        });

    }
]);

angular.module('fixit').controller('UserContributionController', ['SettingService', '$scope', 'username',
    function (SettingService, $scope, username) {

        SettingService.getContributions(username).then(function (contributions) {
            $scope.contributions = contributions;
        });

    }
]);

