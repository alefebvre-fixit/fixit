angular.module('ya-app').config(function ($stateProvider) {
    $stateProvider
        .state('ya.events', {
            url: "/events",
            views: {
                'tab-events': {
                    templateUrl: "templates/events/event-list.html",
                    controller: 'ListEventsController'
                }
            },
            authenticate: true
        })
        .state('ya.event-view', {
            cache: false,
            url: "/events/:eventId",
            views: {
                'tab-events': {
                    templateUrl: "templates/events/event-view.html",
                    controller: 'ViewEventController',
                    resolve: {
                        eventId: function ($stateParams) {
                            console.log('Hello event nb=' + $stateParams.eventId);

                            return $stateParams.eventId;
                        }
                    }
                }
            },
            authenticate: true
        })
        /*
        .state('ya.discover', {
            url: "/discover",
            views: {
                'tab-events': {
                    templateUrl: "templates/events/discover.html",
                    controller: 'DiscoverEventController',
                    resolve: {
                        events: function ($rootScope, eventService) {
                            return eventService.getEvents();

                        }
                    }
                }

            },
            authenticate: true
        })
        .state('ya.event-comments', {
            cache: false,
            url: "/events/:eventId/comments",
            views: {
                'tab-events': {
                    templateUrl: "templates/events/event-comments.html",
                    controller: 'eventCommentsController',
                    resolve: {
                        eventId: function ($stateParams) {
                            return $stateParams.eventId;
                        }
                    }
                }
            },
            authenticate: true
        })
        .state('ya.event-followers', {
            cache: false,
            url: "/events/:eventId/followers",
            views: {
                'tab-events': {
                    templateUrl: "templates/events/event-followers.html",
                    controller: 'eventFollowersController',
                    resolve: {
                        eventId: function ($stateParams) {
                            return $stateParams.eventId;
                        }
                    }
                }
            },
            authenticate: true
        })
        */
    ;

});
