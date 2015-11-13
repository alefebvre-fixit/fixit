angular.module('ya-app').controller('ListGroupsController',
    ['GroupService', 'EventService', '$scope', '$log','$state',
        function (GroupService, EventService, $scope, $log, $state) {

            $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
                GroupService.getGroups().then(function (groups) {
                    $scope.groups = groups;
                });
            });

            $scope.doRefresh = function() {
                GroupService.getGroups().then(function (groups) {
                    $scope.groups = groups;
                    $scope.$broadcast('scroll.refreshComplete');
                });

            };

            $scope.openCreateGroup = function() {
                $state.go('group-create');
            };

        }
    ]);


angular.module('ya-app').controller('EditGroupController',
    ['GroupService', '$scope', '$log', '$state', 'groupId',
        function (GroupService, $scope, $log, $state, groupId) {

            GroupService.getGroup(groupId).then(function(group) {
                $scope.group = group;
            });

            $scope.saveGroup = function(form) {
                $log.log($scope.group);
                // If form is invalid, return and let AngularJS show validation errors.
                if (form.$invalid) {
                    return;
                }

                GroupService.saveGroup($scope.group).then(function(group) {
                    $state.go('group', {groupId: group.id});
                });

            };


        }
    ]);

angular.module('ya-app').controller('CreateGroupController',
    ['GroupService', '$scope', '$log', '$state', '$ionicHistory',
        function (GroupService, $scope, $log, $state, $ionicHistory) {

            $scope.group = {};

            $scope.saveGroup = function(form) {
                $log.log($scope.group);
                // If form is invalid, return and let AngularJS show validation errors.
                if (form.$invalid) {
                    return;
                }

                GroupService.saveGroup($scope.group).then(function(group) {
                    $ionicHistory.currentView($ionicHistory.backView());
                    $state.go('group', {groupId: group.id, location: 'replace'});
                });

            };


        }
    ]);


angular.module('ya-app').controller('ViewGroupController',
    ['YaService', '$scope', '$log', '$state', '$ionicPopup','$ionicPopover', '$ionicActionSheet', '$ionicModal', 'GroupService', 'EventService', 'groupId',
        function (YaService, $scope, $log, $state, $ionicPopup,$ionicPopover, $ionicActionSheet, $ionicModal, GroupService, EventService, groupId) {
            $log.log("ViewGroupController groupId=" + groupId);


            $scope.isFavorite = function(group){
                return YaService.isFavorite(group);
            };



            //To insure the back button is displayed
            $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
                $scope.summary = {followerSize : '-', commentSize : '-', comments: [], lastEvents: []};

                viewData.enableBack = true;

                GroupService.getGroup(groupId).then(function (group) {
                    $log.log("ViewGroupController getGroup is called groupId=" + groupId);
                    $scope.group = group;


                    GroupService.getEventSize(groupId).then(function (data) {
                        $scope.summary.eventSize = data;
                    });

                    GroupService.getLastEvents(groupId).then(function (data) {
                        $scope.summary.lastEvents = data;
                    });

                    GroupService.getFollowerSize(groupId).then(function (data) {
                        $scope.summary.followerSize = data;
                    });

                    GroupService.getCommentSize(groupId).then(function (data) {
                        $scope.summary.commentSize = data;
                    });

                });
            });





            $scope.setGroup =function(newGroup){
                $scope.group = newGroup;
                GroupService.getFollowerSize(groupId).then(function (data) {
                    $scope.summary.followerSize = data;
                });
                GroupService.getCommentSize(groupId).then(function (data) {
                    $scope.summary.commentSize = data;
                });
                GroupService.getComments(groupId).then(function (data) {
                    $scope.summary.comments = data;
                });
            };

            $scope.follow = function(group){
                GroupService.followGroup(group).then(function (favorites) {
                    YaService.setFavorites(favorites);
                    GroupService.getFollowerSize(groupId).then(function (data) {
                        $scope.summary.followerSize = data;
                    });
                    YaService.toastMe('You are now following group ' + group.name);
                });
            };

            $scope.unfollow = function(group){
                var confirmPopup = $ionicPopup.confirm({
                    title: 'Unfollow',
                    template: 'Remove group ' + group.name + ' from favorites ?'
                });
                confirmPopup.then(function(res) {
                    if(res) {
                        GroupService.unfollowGroup(group).then(function (favorites) {
                            YaService.setFavorites(favorites);
                            GroupService.getFollowerSize(groupId).then(function (data) {
                                $scope.summary.followerSize = data;
                            });
                            YaService.toastMe('You are no longer following group ' + group.name);
                        });
                    }
                });
            };

            $scope.deleteGroup = function(groupToDelete) {
                $scope.closePopover();
                var confirmPopup = $ionicPopup.confirm({
                    title: 'Delete Group',
                    template: 'Are you sure you want to delete this group?',
                    buttons: [
                        { text: 'Cancel' },
                        {
                            text: '<b>Delete</b>',
                            type: 'button-calm',
                            onTap: function(e) {
                               return true;
                            }
                        }
                    ]
                });
                confirmPopup.then(function(res) {
                    if(res) {
                        GroupService.deleteGroup(groupToDelete).then(function () {
                            YaService.toastMe('Group ' + groupToDelete.name + ' deleted.');
                            $state.go('tabs.groups');
                        });
                    }
                });
            };


            $scope.openEditGroup = function(group){
                $scope.closePopover();
                $state.go("group-edit", { groupId: group.id});
            };

            $scope.openCreateEvent = function(group) {
                $scope.closePopover();
                $state.go("event-create", { groupId: group.id});
            };


            $ionicPopover.fromTemplateUrl('templates/groups/partial/group-popover.html', {
                scope: $scope
            }).then(function(popover) {
                $scope.popover = popover;
            });

            $scope.closePopover = function() {
                if ($scope.popover){
                    $scope.popover.hide();
                }
            };
            //Cleanup the popover when we're done with it!
            $scope.$on('$destroy', function() {
                $scope.popover.remove();
            });


        }
    ]);

angular.module('ya-app').controller('GroupFollowersController', ['GroupService', '$scope', '$log', 'groupId',
    function (GroupService, $scope, $log, groupId) {

        //To insure the back button is displayed
        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            viewData.enableBack = true;
        });

        GroupService.getFollowers(groupId).then(function (followers) {
            $scope.followers = followers;
        });

    }
]);


angular.module('ya-app').controller('GroupEventsController',
    ['events', 'groupId', 'GroupService', '$scope', '$log',
        function (events, groupId, GroupService, $scope, $log) {

            //To insure the back button is displayed
            $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
                viewData.enableBack = true;
            });

            $log.log("GroupEventsController is called");

            $scope.events = events;
            $scope.groupId = groupId;

            $scope.doRefresh = function() {
                GroupService.getEvents($scope.groupId).then(function (events) {
                    $scope.events = events;
                });
                $scope.$broadcast('scroll.refreshComplete');
            };

        }
    ]);