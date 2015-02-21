angular.module('fixit').factory('SettingService', ['$http', '$rootScope', function($http, $rootScope) {

    var resultService;
    resultService = {
        getAccount: function () {
            return $http.get($rootScope.baseUrl + '/api/account').then(function (response) {
                return response.data;
            });
        },
        getAccountSummary: function () {
            return $http.get($rootScope.baseUrl + '/api/account/summary').then(function (response) {
                return response.data;
            });
        },
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
        getUserProfile: function (username) {
            return $http.get($rootScope.baseUrl + '/api/users/' + username).then(function (response) {
                return response.data;
            });
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
            return $http.get($rootScope.baseUrl + '/api/users/' + username + '/favorites').then(function (response) {
                return response.data;
            });
        }
    };

    return resultService;
}]);


