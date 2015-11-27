angular.module('ya-app').factory('YaService',
    ['$rootScope', '$log',
        function($rootScope, $log) {
            var resultService;
            resultService = {
                toastMe: function(message) {
                    if ($rootScope.isPluginEnabled){
                        $cordovaToast.show(message, 'short', 'center').then(
                            function(success) {
                                // success
                            }, function(error) {
                                // error
                            });
                    } else {
                        $log.log('$cordovaToast will show:' + message);
                    }

                },
                setUser: function(user){
                    $rootScope.user = user;
                    if (user){
                        localStorage.setItem("username",user.username);
                    } else {
                        localStorage.removeItem("username");
                    }
                },
                setFavorites: function(favorites){
                    $log.log("setFavorites from service" + favorites);

                    $rootScope.favorites = favorites;
                },
                isFavorite: function(group){
                    if (group){
                        $log.log("isFavorite " + group.id);
                        return ($rootScope.favorites.indexOf(group.id) >= 0);
                    }
                    return false;
                },
                isMine: function(group){
                    if (group){
                        if (group.username){
                            return (group.username == $rootScope.user.username);
                        }
                        else if (group.contributor){
                            return (group.contributor == $rootScope.user.username);
                        }
                    }
                    return false;
                },
                getUsername: function(){
                    return $rootScope.user.username;
                }
            };
            return resultService;
        }]);



