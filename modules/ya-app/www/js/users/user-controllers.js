angular.module('ya-app').controller('SignUpController', ['YaService', 'UserService', '$scope', '$log', '$state', 'signup', '$ionicLoading',
    function (YaService, UserService, $scope, $log,  $state, signup, $ionicLoading) {
        $log.log("Enter SignUpController");

        $scope.signup = signup;

        $scope.doSignUp = function () {

            UserService.signupUser($scope.signup).success(function (data) {
                YaService.setUser(data);
                $state.transitionTo('tabs.events');
            }).error(function (response, status) {
                $log.log("Invalid sign-up");
                $scope.signup.error = 'Invalid sign-up';
            });
        };

        $scope.goToSignIn = function () {
            $state.transitionTo('sign-in');
        };
    }
]);

angular.module('ya-app').controller('SignInController', ['YaService', 'UserService', '$scope', '$log', '$state', '$ionicLoading',
    function (YaService, UserService, $scope, $log, $state, $ionicLoading) {

        $scope.signin = {username: 'antoinelefebvre', password: 'password'};
        // Perform the login action when the user submits the login for
        $scope.doSignIn = function () {

            $ionicLoading.show({
                template: '<ion-spinner class="spinner-calm"></ion-spinner>'
            });

            UserService.signinUser($scope.signin).success(function (user) {

                UserService.getFollowingGroupIds(user.username).then(function(favorites) {
                        YaService.setFavorites(favorites);
                        UserService.getFollowingNames(user.username).then(function(following) {
                            YaService.setFollowing(following);
                            YaService.setUser(user);
                            $state.transitionTo('tabs.events');
                            $ionicLoading.hide();
                        });
                    }
                );
            }).error(function (response, status) {
                $ionicLoading.hide();
                $log.log("Invalid username or password");
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
                $log.log(JSON.stringify(result));
                $scope.signin.error = JSON.stringify(result);


                UserService.signInGoogle(result).success(function (user) {
                    $ionicLoading.hide();

                }).error(function (response, status) {
                    $ionicLoading.hide();
                    $log.log("Invalid username or password");
                    $scope.signin.error = 'Invalid username or password';
                });


                $ionicLoading.hide();
            }, function(error) {
                $log.log(error);
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



angular.module('ya-app').controller('EditUserController', ['YaService', 'UserService', '$scope', '$log', 'profile','$state',
    function (YaService, UserService, $scope, $log, profile, $state) {

        $scope.profile = profile;

        $scope.saveProfile = function(profile) {
            UserService.saveProfile(profile).then(function (data) {
                YaService.setUser(data);
                $scope.profile = data.profile;
                YaService.toastMe('Setting Updated');
                $state.go("user", {username : $scope.user.username});
            });
        };

    }
]);


angular.module('ya-app').controller('UserController', ['YaService', '$scope', '$log', 'UserService', 'username', '$ionicPopup', '$ionicPopover','$state',
    function (YaService, $scope, $log, UserService, username, $ionicPopup, $ionicPopover, $state) {



        $scope.summary = {groupSize : '-', followingSize : '-', followerSize : '-', user: {}, followingGroups : []};

        var reload = function(username){

            UserService.getUserSummary(username).then(function (summary) {
                $scope.summary.user = summary;
            });

            UserService.getFollowingGroupsSize(username).then(function (groupSize) {
                $scope.summary.groupSize = groupSize;
            });

            UserService.getFollowersSize(username).then(function (followerSize) {
                $scope.summary.followerSize = followerSize;
            });

            UserService.getFollowingSize(username).then(function (followingSize) {
                $scope.summary.followingSize = followingSize;
            });

            UserService.getFollowingGroups(username).then(function (groups) {
                $scope.summary.followingGroups = groups;
            });

        };

        //To insure the back button is displayed
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
            reload(username);
        });

        $scope.follow = function(username){
            $log.log("follow=" + username);
            UserService.follow(username).then(function (data) {
                YaService.addFollowing(username);
                UserService.getFollowersSize(username).then(function (followerSize) {
                    $scope.summary.followerSize = followerSize;
                });
            });
        };

        $scope.unfollow = function(username){
            var confirmPopup = $ionicPopup.confirm({
                title: 'Unfollow',
                template: 'Stop following ' + username + ' ?',
                type: 'button-calm'
            });
            confirmPopup.then(function(res) {
                if(res) {
                    $log.log("unfollow=" + username);
                    UserService.unfollow(username).then(function (data) {
                        YaService.removeFollowing(username);
                        UserService.getFollowersSize(username).then(function (followerSize) {
                            $scope.summary.followerSize = followerSize;
                        });
                    });
                }
            });
        };

        $scope.isFollowing = function(username){
            return YaService.isFollowing(username);
        };

        $ionicPopover.fromTemplateUrl('templates/users/partial/user-popover.html', {
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


angular.module('ya-app').controller('FollowersController', ['UserService', '$scope', '$log', 'username',
    function (UserService, $scope, $log, username) {

        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            UserService.getFollowers(username).then(function (followers) {
                $scope.users = followers;
            });
        });

        $scope.doRefresh = function() {
            UserService.getFollowers(username).then(function (followers) {
                $scope.users = followers;
                $scope.$broadcast('scroll.refreshComplete');
            });
        };


    }
]);

angular.module('ya-app').controller('FollowingController', ['UserService', '$scope', '$log', 'username',
    function (UserService, $scope, $log, username) {

        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            UserService.getFollowing(username).then(function (followers) {
                $scope.users = followers;
            });
        });

        $scope.doRefresh = function() {
            UserService.getFollowing(username).then(function (followers) {
                $scope.users = followers;
                $scope.$broadcast('scroll.refreshComplete');
            });
        };


    }
]);


angular.module('ya-app').controller('UserGroupController', ['UserService', '$scope', '$log', 'username',
    function (UserService, $scope, $log, username) {

        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            UserService.getFollowingGroups(username).then(function (groups) {
                $scope.groups = groups;
            });
        });

        $scope.doRefresh = function() {
            UserService.getFollowingGroups(username).then(function (groups) {
                $scope.groups = groups;
                $scope.$broadcast('scroll.refreshComplete');
            });
        };


    }
]);


angular.module('ya-app').controller('UserDiscoveryController', ['UserService', '$scope', '$log',
    function (UserService, $scope, $log) {

        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            UserService.getUserDiscovery().then(function (users) {
                $scope.users = users;
            });
        });

        $scope.doRefresh = function() {
            UserService.getUserDiscovery().then(function (users) {
                $scope.users = users;
            });
        };


    }
]);