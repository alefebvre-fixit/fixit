angular.module('ya-app').config(function ($stateProvider) {
    $stateProvider
        .state('ya.groups', {
            url: "/groups",
            views: {
                'ya-groups': {
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
                'ya-groups': {
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
        .state('ya.group-new', {
            url: "/groups/new",
            views: {
                'menuContent': {
                    templateUrl: "templates/groups/group-new.html",
                    controller: 'EditGroupController',
                    resolve: {
                        group: function ($rootScope, groupService) {
                            console.log('groupService.instanciateGroup()' + JSON.stringify(groupService.instanciateGroup()));
                            return groupService.instanciateGroup();
                        }
                    }
                }
            },
            authenticate: false
        })

        .state('ya.group-edit', {
            cache: false,
            url: "/groups/:groupId/edit",
            views: {
                'menuContent': {
                    templateUrl: "templates/groups/group-edit.html",
                    controller: 'EditGroupController',
                    resolve: {
                        group: function ($stateParams, groupService) {
                            console.log('edit-group: resolve single group');
                            return groupService.getGroup($stateParams.groupId);
                        }
                    }
                }
            },
            authenticate: true

        })
        .state('ya.discover', {
            url: "/discover",
            views: {
                'menuContent': {
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
                'menuContent': {
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
                'menuContent': {
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
