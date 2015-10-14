angular.module('ya-app').factory('SettingService', ['$http', '$rootScope', function($http, $rootScope) {

    var resultService;
    resultService = {
        getUserSummary: function (username) {
            return $http.get($rootScope.baseUrl + '/api/users/' + username + '/summary').then(function (response) {
                return response.data;
            });
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
        getFollowers: function (username) {
            return $http.get($rootScope.baseUrl + '/api/users/' + username + '/followers').then(function (response) {
                return response.data;
            });
        },
        getFavorites: function (username) {
            return $http.get($rootScope.baseUrl + '/api/users/' + username + '/favorites/groups').then(function (response) {
                return response.data;
            });
        }
    };

    return resultService;
}]);


