angular.module('ya-app').factory('UserService', ['$http', '$log', '$rootScope','User',
                            function($http, $log, $rootScope, User) {

    var resultService;
    resultService = {
        getUser: function (username) {
            return User.find(username);
            /*
            return $http.get($rootScope.baseUrl + '/api/users/' + username ).then(function (response) {
                return response.data;
            });
            */
        },
        saveProfile: function (profile) {
            return $http.post($rootScope.baseUrl + '/api/profile', profile).then(function (response) {
                return response.data;
            });
        },
        signupUser: function (signup) {
            return $http.post($rootScope.baseUrl + '/api/signup', signup);
        },
        signinUser: function (signin) {
            return $http.post($rootScope.baseUrl + '/api/signin', signin);
        },
        signInGoogle: function (signin) {
            return $http.post($rootScope.baseUrl + '/api/signin/google', signin);
        },
        follow: function (username) {
            return $http.post($rootScope.baseUrl + '/api/users/'+ username +'/follow').then(function (response) {
                return response.data;
            });
        },
        unfollow: function (username) {
            return $http.post($rootScope.baseUrl + '/api/users/'+ username +'/unfollow').then(function (response) {
                return response.data;
            });
        },
        getFollowingGroups: function (username) {
            return $http.get($rootScope.baseUrl + '/api/users/' + username + '/groups/following').then(function (response) {
                return response.data;
            });
        },
        getFollowingGroupsSize: function (username) {
            return $http.get($rootScope.baseUrl + '/api/users/' + username + '/groups/following/size').then(function (response) {
                return response.data;
            });
        },
        getFollowingGroupIds: function (username) {
            return $http.get($rootScope.baseUrl + '/api/users/' + username + '/groups/following/id').then(function (response) {
                return response.data;
            });
        },
        getFollowers: function (username) {
            return $http.get($rootScope.baseUrl + '/api/users/' + username + '/followers').then(function (response) {
                return response.data;
            });
        },
        getFollowersSize: function (username) {
            return $http.get($rootScope.baseUrl + '/api/users/' + username + '/followers/size').then(function (response) {
                return response.data;
            });
        },
        getFollowingSize: function (username) {
            return $http.get($rootScope.baseUrl + '/api/users/' + username + '/following/size').then(function (response) {
                return response.data;
            });
        },
        getFollowing: function (username) {
            return $http.get($rootScope.baseUrl + '/api/users/' + username + '/following').then(function (response) {
                return response.data;
            });
        },
        getFollowingNames: function (username) {
            return $http.get($rootScope.baseUrl + '/api/users/' + username + '/following/name').then(function (response) {
                return response.data;
            });
        },
        getUserDiscovery: function () {
            return $http.get($rootScope.baseUrl + '/api/users/discovery').then(function (response) {
                User.inject(response.data);
                return response.data;
            });
        },
        getUsers: function () {
            return User.findAll();
        }
    };

    return resultService;
}]);


