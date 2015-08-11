angular.module('ya-app').factory('EventService',
    ['$http', '$rootScope',
        function($http, $rootScope) {

            var resultService;
            resultService = {
                getEvents: function () {
                    return $http.get($rootScope.baseUrl + '/api/events').then(function (response) {
                        return response.data;
                    });
                },
                getEvent: function (eventId) {
                    return $http.get($rootScope.baseUrl + '/api/events/' + eventId).then(function (response) {
                        return response.data;
                    });
                },
                saveEvent: function (event) {
                    return $http.post($rootScope.baseUrl + '/api/events', event).then(function (response) {
                        return response.data;
                    });
                },
                getCommentSize: function (eventId) {
                    return $http.get($rootScope.baseUrl + '/api/events/' + eventId + '/comments/size').then(function (response) {
                        return response.data;
                    });
                },
                getComments: function (eventId) {
                    return $http.get($rootScope.baseUrl + '/api/events/' + eventId + '/comments').then(function (response) {
                        return response.data;
                    });
                },
                postComment: function (eventId, content) {
                    return $http.post($rootScope.baseUrl + '/api/events/' + eventId + '/comments/' + content).then(function (response) {
                        return response.data;
                    });
                },
                deleteEvent: function (event) {
                    return $http.post($rootScope.baseUrl + '/api/events/' + event.id + '/delete').then(function (response) {
                        return response.data;
                    });
                },
                instanciateEvent: function () {
                    return $http.get($rootScope.baseUrl + '/api/events/new').then(function (response) {
                        return response.data;
                    });
                }
            };

            return resultService;
        }]);


