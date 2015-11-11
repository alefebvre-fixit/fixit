angular.module('ya-app').factory('NotificationService',
    ['$http', '$rootScope',
        function($http, $rootScope) {

            var resultService;
            resultService = {
                getNotifications: function () {
                    return $http.get($rootScope.baseUrl + '/api/notifications').then(function (response) {
                        return response.data;
                    });
                },
                acknowledgeNotification: function (notification) {
                    return $http.post($rootScope.baseUrl + '/api/notifications/' + notification.id + '/acknowledge').then(function (response) {
                        return response.data;
                    });
                },
                acknowledgeNotifications: function () {
                    return $http.post($rootScope.baseUrl + '/api/notifications/acknowledge').then(function (response) {
                        return response.data;
                    });
                },
                acknowledgeEventNotifications: function (eventId) {
                    return $http.post($rootScope.baseUrl + '/api/events/' + eventId  + '/notifications/acknowledge').then(function (response) {
                        return response.data;
                    });
                },
                acknowledgeGroupNotifications: function (groupId) {
                    return $http.post($rootScope.baseUrl + '/api/groups/' + groupId  + '/notifications/acknowledge').then(function (response) {
                        return response.data;
                    });
                }

            };

            return resultService;
        }]);
