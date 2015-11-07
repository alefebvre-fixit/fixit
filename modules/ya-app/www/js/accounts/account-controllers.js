angular.module('ya-app').controller('SignUpController', ['YaService', 'SettingService', '$scope', '$state', 'signup', '$ionicLoading',
    function (YaService, SettingService, $scope, $state, signup, $ionicLoading) {
        console.log("Enter SignUpController");

        $scope.signup = signup;

        $scope.doSignUp = function () {

            SettingService.signupUser($scope.signup).success(function (data) {
                $scope.setUser(data);
                $state.transitionTo('tabs.groups');
            }).error(function (response, status) {
                console.log("Invalid sign-up");
                $scope.signup.error = 'Invalid sign-up';
            });
        };

        $scope.goToSignIn = function () {
            $state.transitionTo('sign-in');
        };
    }
]);

angular.module('ya-app').controller('SignInController', ['YaService', 'SettingService', '$scope', '$state', '$ionicLoading',
    function (YaService, SettingService, $scope, $state, $ionicLoading) {

        $scope.signin = {username: 'antoinelefebvre', password: 'password'};
        // Perform the login action when the user submits the login for
        $scope.doSignIn = function () {

            $ionicLoading.show({
                template: '<ion-spinner class="spinner-calm"></ion-spinner>'
            });

            SettingService.signinUser($scope.signin).success(function (user) {
                SettingService.getFavorites(user.username).then(function(favorites) {
                        YaService.setFavorites(favorites);
                        $scope.setUser(user);
                        $state.transitionTo('tabs.groups');
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
            $state.go('sign-up');
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



angular.module('ya-app').controller('EditSettingController', ['YaService', 'SettingService', '$scope', 'profile', '$cordovaToast',
    function (YaService, SettingService, $scope, profile, $cordovaToast) {

        $scope.profile = profile;

        $scope.saveProfile = function(profile) {
            SettingService.saveProfile(profile).then(function (data) {
                YaService.setUser(data);
                $scope.profile = data.profile;
                YaService.toastMe('Setting Updated');
            });
        };

    }
]);


angular.module('ya-app').controller('UserController', ['YaService', '$scope', 'SettingService', 'username', '$ionicPopup', '$ionicPopover','$state',
    function (YaService, $scope, SettingService, username, $ionicPopup, $ionicPopover, $state) {

        //To insure the back button is displayed
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });

        SettingService.getUserSummary(username).then(function (summary) {
            $scope.summary = summary;
        });

        $scope.follow = function(username){
            console.log("follow=" + username);
            SettingService.follow(username).then(function (data) {
                YaService.setUser(data);
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
                        YaService.setUser(data);
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

        $ionicPopover.fromTemplateUrl('templates/accounts/partial/account-popover.html', {
            scope: $scope
        }).then(function(popover) {
            $scope.popover = popover;
        });

        $scope.closePopover = function() {
            if ($scope.popover){
                $scope.popover.hide();
            }
        };
        //Cleanup the popover when we're done with it!
        $scope.$on('$destroy', function() {
            $scope.popover.remove();
        });

        $scope.openEditAccount = function(user){
            $scope.closePopover();
            $state.go("tabs.settings");
        };
    }
]);


angular.module('ya-app').controller('FollowersController', ['SettingService', '$scope', 'username',
    function (SettingService, $scope, username) {

        SettingService.getFollowers(username).then(function (followers) {
            $scope.followers = followers;
        });

    }
]);



