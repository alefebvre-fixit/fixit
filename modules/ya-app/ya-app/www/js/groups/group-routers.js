angular.module('ya-app').config(function ($stateProvider) {
    $stateProvider
        .state('ya.groups', {
            url: "/groups",
            views: {
                'tab-groups': {
                    templateUrl: "templates/groups/group-list.html",
                    controller: 'ListGroupsController'
                }
            },
            authenticate: true
        })
        .state('ya.group-view', {
            cache: false,
            url: "/groups/:groupId",
            views: {
                'tab-groups': {
                    templateUrl: "templates/groups/group-view.html",
                    controller: 'ViewGroupController',
                    resolve: {
                        groupId: function ($stateParams) {
                            console.log('Hello group nb=' + $stateParams.groupId);

                            return $stateParams.groupId;
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
                'tab-groups': {
                    templateUrl: "templates/groups/discover.html",
                    controller: 'DiscoverGroupController',
                    resolve: {
                        groups: function ($rootScope, groupService) {
                            return groupService.getGroups();

                        }
                    }
                }

            },
            authenticate: true
        })
        .state('ya.group-comments', {
            cache: false,
            url: "/groups/:groupId/comments",
            views: {
                'tab-groups': {
                    templateUrl: "templates/groups/group-comments.html",
                    controller: 'groupCommentsController',
                    resolve: {
                        groupId: function ($stateParams) {
                            return $stateParams.groupId;
                        }
                    }
                }
            },
            authenticate: true
        })
        .state('ya.group-followers', {
            cache: false,
            url: "/groups/:groupId/followers",
            views: {
                'tab-groups': {
                    templateUrl: "templates/groups/group-followers.html",
                    controller: 'groupFollowersController',
                    resolve: {
                        groupId: function ($stateParams) {
                            return $stateParams.groupId;
                        }
                    }
                }
            },
            authenticate: true
        })
        */
    ;

});
