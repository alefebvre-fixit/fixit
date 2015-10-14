angular.module('ya-app').controller('ListGroupsController',
    ['GroupService', 'EventService', '$scope', '$ionicModal',
        function (GroupService, EventService, $scope, $ionicModal) {

            GroupService.getGroups().then(function (groups) {
                $scope.groups = groups;
            });

            $scope.doRefresh = function() {
                GroupService.getGroups().then(function (groups) {
                    $scope.groups = groups;
                });
                $scope.$broadcast('scroll.refreshComplete');
            };


            //Start Modal for group editing
            $ionicModal.fromTemplateUrl('templates/groups/group-create-modal.html', {
                scope: $scope,
                animation: 'slide-in-up'
            }).then(function(modal) {
                $scope.groupCreateModal = modal;
            });

            $scope.openCreateGroup = function() {
                $scope.myGroup = {};
                $scope.groupCreateModal.show();
            };

            $scope.closeCreateGroup = function() {
                $scope.groupCreateModal.hide();
            };

            //Cleanup the modal when we're done with it!
            $scope.$on('$destroy', function() {
                if ($scope.groupCreateModal){
                    $scope.groupCreateModal.remove();
                }
            });

            // Execute action on hide modal
            $scope.$on('groupCreateModal.hidden', function() {
                // Execute action
            });

            // Execute action on remove modal
            $scope.$on('groupCreateModal.removed', function() {
                // Execute action
            });

            $scope.save = function(myGroup) {
                //TODO Do some validation
                console.log(myGroup);

                GroupService.saveGroup(myGroup).then(function(data){
                    $scope.groups.push(data);
                    $scope.closeCreateGroup();
                });


            };

            //End Modal for group editing

        }
    ]);


angular.module('ya-app').controller('ViewGroupController',
    ['YaService', '$scope', '$state', '$ionicPopup', '$ionicActionSheet', '$ionicModal', 'GroupService', 'EventService', 'groupId',
        function (YaService, $scope, $state, $ionicPopup, $ionicActionSheet, $ionicModal, GroupService, EventService, groupId) {
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
                var confirmPopup = $ionicPopup.confirm({
                    title: 'Delete Group',
                    template: 'Are you sure you want to delete this group?'
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


                // Triggered on a button click, or some other target
            $scope.showGroupAction = function(groupToUpdate) {
                // Show the action sheet
                $ionicActionSheet.show({
                    buttons: [
                        { text: 'Edit' },
                        { text: 'Create Event' }
                    ],
                    destructiveText: 'Delete',
                    titleText: 'Group',
                    cancelText: 'Cancel',
                    destructiveButtonClicked: function() {
                        $scope.deleteGroup(groupToUpdate);
                    },
                    buttonClicked: function(index) {
                        if (index == 0){
                            $scope.openEditGroup(groupToUpdate);
                        } else if (index == 1) {
                            $scope.openCreateEvent(groupToUpdate);
                        }
                        return true;
                    }
                });
            };



            //Start Modal for group edition

            $ionicModal.fromTemplateUrl('templates/groups/group-edit-modal.html', {
                scope: $scope,
                animation: 'slide-in-up'
            }).then(function(modal) {
                $scope.groupEditModal = modal;
            });

            $scope.openEditGroup = function(groupToUpdate) {
                $scope.myGroup = groupToUpdate;
                $scope.groupEditModal.show();
            };

            $scope.closeEditGroup = function() {
                $scope.groupEditModal.hide();
            };

            //Cleanup the modal when we're done with it!
            $scope.$on('$destroy', function() {
                if ($scope.groupEditModal){
                    $scope.groupEditModal.remove();
                }
            });

            // Execute action on hide modal
            $scope.$on('groupEditModal.hidden', function() {
                // Execute action
            });

            // Execute action on remove modal
            $scope.$on('groupEditModal.removed', function() {
                // Execute action
            });

            $scope.saveGroup = function(myGroup) {
                GroupService.saveGroup(myGroup).then(function(data){
                    $scope.group = data;
                    $scope.closeEditGroup();
                });
            };

            //End Modal for group edition

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