angular.module('ya-app').controller('ListGroupsController',
    ['GroupService', 'EventService', '$scope','$state', '$ionicModal',
        function (GroupService, EventService, $scope, $state, $ionicModal) {

            GroupService.getGroups().then(function (groups) {
                $scope.groups = groups;
            });

            $scope.doRefresh = function() {
                GroupService.getGroups().then(function (groups) {
                    $scope.groups = groups;
                });
                $scope.$broadcast('scroll.refreshComplete');
            };

            $scope.openCreateGroup = function() {
                $state.go('group-new');
            };

        }
    ]);


angular.module('ya-app').controller('EditGroupController',
    ['GroupService', '$scope', '$state', 'groupId',
        function (GroupService, $scope, $state, groupId) {

            if (groupId > 0){
                GroupService.getGroup(groupId).then(function(group) {
                    $scope.group = group;
                });
            } else {
                $scope.group = {};
            }

            $scope.saveGroup = function(form) {
                console.log($scope.group);
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


angular.module('ya-app').controller('ViewGroupController',
    ['YaService', '$scope', '$state', '$ionicPopup','$ionicPopover', '$ionicActionSheet', '$ionicModal', 'GroupService', 'EventService', 'groupId',
        function (YaService, $scope, $state, $ionicPopup,$ionicPopover, $ionicActionSheet, $ionicModal, GroupService, EventService, groupId) {
            console.log("ViewGroupController groupId=" + groupId);


            $scope.isFavorite = function(group){
                return YaService.isFavorite(group);
            };

            //To insure the back button is displayed
            $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
                viewData.enableBack = true;
            });

            $scope.summary = {followerSize : '-', commentSize : '-', comments: [], lastEvents: []};

            GroupService.getGroup(groupId).then(function (group) {
                console.log("ViewGroupController getGroup is called groupId=" + groupId);
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



            //Start Modal for event creation
            $ionicModal.fromTemplateUrl('templates/events/event-create-modal.html', {
                scope: $scope,
                animation: 'slide-in-up'
            }).then(function(modal) {
                $scope.eventCreateModal = modal;
            });

            $scope.openCreateEvent = function(group) {
                EventService.instanciateEvent(group).then(function(data){
                    $scope.myEvent = data;
                    $scope.eventCreateModal.show();
                });
            };

            $scope.closeCreateEvent = function() {
                $scope.eventCreateModal.hide();
            };

            //Cleanup the modal when we're done with it!
            $scope.$on('$destroy', function() {
                if ($scope.eventCreateModal){
                    $scope.eventCreateModal.remove();
                }
            });

            // Execute action on hide modal
            $scope.$on('eventCreateModal.hidden', function() {
                // Execute action
            });

            // Execute action on remove modal
            $scope.$on('eventCreateModal.removed', function() {
                // Execute action
            });

            $scope.saveEvent = function(myEvent) {
                //TODO Do some validation
                console.log(myEvent);

                EventService.saveEvent(myEvent).then(function(data){
                    $scope.closeCreateEvent();
                    $state.go('event', {eventId: data.id});
                });


            };

            //End Modal for event creation

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

angular.module('ya-app').controller('GroupFollowersController', ['GroupService', '$scope', 'groupId',
    function (GroupService, $scope, groupId) {

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
    ['events', 'groupId', 'GroupService', '$scope',
        function (events, groupId, GroupService, $scope) {

            //To insure the back button is displayed
            $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
                viewData.enableBack = true;
            });

            console.log("GroupEventsController is called");

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