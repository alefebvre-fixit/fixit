angular.module('ya-app').factory('NotificationService',
    ['$http', '$rootScope',
        function($http, $rootScope) {

            var resultService;
            resultService = {
                getNotifications: function () {
                    return $http.get($rootScope.baseUrl + '/api/notifications').then(function (response) {
                        return response.data;
                    });
                }
            };

            return resultService;
        }]);
