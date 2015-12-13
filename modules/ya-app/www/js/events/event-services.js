angular.module('ya-app').factory('EventService',
    ['$http', '$rootScope', '$filter', 'YaService', 'Event',
        function($http, $rootScope, $filter, YaService, Event) {

            var resultService;
            resultService = {
                getEvents: function () {
                    return Event.findAll();
                    /*
                    return $http.get($rootScope.baseUrl + '/api/events').then(function (response) {
                        return response.data;
                    });
                    */
                },
                getEventTimeline: function () {
                    return $http.get($rootScope.baseUrl + '/api/events/timeline').then(function (response) {
                        Event.inject(response.data.today);
                        Event.inject(response.data.upcoming);
                        Event.inject(response.data.past);
                        return response.data;
                    });
                },
                getEvent: function (eventId) {
                    return Event.find(eventId);
                    /*
                    return $http.get($rootScope.baseUrl + '/api/events/' + eventId).then(function (response) {
                        return response.data;
                    });
                    */
                },
                saveEvent: function (event) {
                    if (event.id){
                        return Event.update(event.id, event);
                    } else {
                        return Event.create(event);
                    }
                    /*
                    return $http.post($rootScope.baseUrl + '/api/events', event).then(function (response) {
                        return response.data;
                    });
                    */
                },
                getCommentSize: function (event) {
                    return $http.get($rootScope.baseUrl + '/api/events/' + event.id + '/comments/size').then(function (response) {
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
                    return Event.destroy(event.id);
                    /*
                    return $http.post($rootScope.baseUrl + '/api/events/' + event.id + '/delete').then(function (response) {
                        return response.data;
                    });
                    */
                },
                instanciateEvent: function (groupId) {
                    return $http.get($rootScope.baseUrl + '/api/groups/' + groupId +'/events/new').then(function (response) {
                        return response.data;
                    });
                },
                instanciateParticipation: function(event){
                  participation = {eventId : event.id, userName : $rootScope.user.username};
                },
                getEventParticipations: function(eventId) {
                    return $http.get($rootScope.baseUrl + '/api/events/' + eventId +'/participations').then(function (response) {
                        return response.data;
                    });
                },
                getLastParticipations: function(event) {
                    return $http.get($rootScope.baseUrl + '/api/events/' + event.id +'/participations/last').then(function (response) {
                        return response.data;
                    });
                },
                getParticipationSummary: function(eventId) {
                    return $http.get($rootScope.baseUrl + '/api/events/' + eventId +'/participations/summary').then(function (response) {
                        return response.data;
                    });
                },
                getUserParticipation: function(event) {
                    return $http.get($rootScope.baseUrl + '/api/users/' + $rootScope.user.username + '/events/' + event.id + '/participation').then(function (response) {
                        return response.data;
                    });
                },
                participate: function(participation){
                    return $http.post($rootScope.baseUrl + '/api/events/' + participation.eventId + '/participations', participation).then(function (response) {
                        return response.data;
                    });
                },
                generateParticipations: function(event){
                    return $http.post($rootScope.baseUrl + '/api/events/' + event.id + '/participations/generate').then(function (response) {
                        return response.data;
                    });
                },
                getParticipationsSize: function(event) {
                    return $http.get($rootScope.baseUrl + '/api/events/' + event.id +'/participations/size').then(function (response) {
                        return response.data;
                    });
                }


            };

            return resultService;
        }]);


