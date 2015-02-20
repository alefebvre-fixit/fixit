angular.module('fixit').factory('SettingService', ['$http', '$rootScope', function($http, $rootScope) {

    var resultService;
    resultService = {
        getAccount: function () {
            var promise = $http.get($rootScope.baseUrl + '/api/account').then(function (response) {
                return response.data;
            });
            return promise;
        },
        getAccountSummary: function () {
            var promise = $http.get($rootScope.baseUrl + '/api/account/summary').then(function (response) {
                return response.data;
            });
            return promise;
        },
        getUserSummary: function (username) {
            var promise = $http.get($rootScope.baseUrl + '/api/users/' + username + '/summary').then(function (response) {
                return response.data;
            });
            return promise;
        },
        saveProfile: function (profile) {
            var promise = $http.post($rootScope.baseUrl + '/api/profile', profile).then(function (response) {
                return response.data;
            });
            return promise;
        },
        signupUser: function (signup) {
            console.log("Posting signupUser" + signup.email);
            return $http.post($rootScope.baseUrl + '/api/signup', signup);
        },
        signinUser: function (signin) {
            console.log("BBPosting signinUser:" + $rootScope.baseUrl + '/api/signin' + signin.username + '/' + signin.password);
            return $http.post($rootScope.baseUrl + '/api/signin', signin);
        },
        getUserProfile: function (username) {
            console.log("getUser username:" + $rootScope.baseUrl + '/api/users/' + username);
            var promise = $http.get($rootScope.baseUrl + '/api/users/' + username).then(function (response) {
                return response.data;
            });
            return promise;
        },
        follow: function (username) {
            var promise = $http.post($rootScope.baseUrl + '/api/users/'+ username +'/follow').then(function (response) {
                return response.data;
            });
            return promise;
        },
        unfollow: function (username) {
            var promise = $http.post($rootScope.baseUrl + '/api/users/'+ username +'/unfollow').then(function (response) {
                return response.data;
            });
            return promise;
        },
        getFollowers: function (username) {
            var promise = $http.get($rootScope.baseUrl + '/api/users/' + username + '/followers').then(function (response) {
                return response.data;
            });
            return promise;
        }
    };

    return resultService;
}]);


