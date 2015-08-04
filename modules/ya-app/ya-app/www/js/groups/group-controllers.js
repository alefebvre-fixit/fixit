angular.module('ya-app').controller('ListGroupsController',
    ['GroupService', '$scope', '$ionicModal',
        function (GroupService, $scope, $ionicModal) {

            GroupService.getGroups().then(function (groups) {
                $scope.groups = groups;
            });

            $scope.doRefresh = function() {
                GroupService.getGroups().then(function (groups) {
                    $scope.groups = groups;
                });
                $scope.$broadcast('scroll.refreshComplete');
            };



            //Start Modal
            $ionicModal.fromTemplateUrl('templates/groups/group-edit-modal.html', {
                scope: $scope,
                animation: 'slide-in-up'
            }).then(function(modal) {
                $scope.groupEditModal = modal;
            });

            $scope.openEditModal = function() {
                $scope.myGroup = {};
                $scope.groupEditModal.show();
            };

            $scope.closeEditModal = function() {
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

            $scope.create = function(myGroup) {
                //TODO Do some validation
                console.log(myGroup);

                GroupService.saveGroup(myGroup).then(function(data){
                    $scope.groups.push(data);
                });


            };

            //End Modal



        }
    ]);


angular.module('ya-app').controller('ViewGroupController',
    ['$scope', '$ionicPopup', 'GroupService', 'groupId',
        function ($scope, $ionicPopup, GroupService, groupId) {
            console.log("ViewGroupController groupId=" + groupId);


            $scope.summary = {followerSize : 0, commentSize : 0, comments: []};

            GroupService.getGroup(groupId).then(function (group) {
                console.log("ViewGroupController getGroup is called groupId=" + groupId);
                $scope.group = group;
                GroupService.getFollowerSize(groupId).then(function (data) {
                    $scope.summary.followerSize = data;
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
                    $scope.setFavorites(favorites);
                    GroupService.getFollowerSize(groupId).then(function (data) {
                        $scope.summary.followerSize = data;
                    });
                    $scope.toastMe('Group ' + group.name + ' is added to your favorites.');
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
                            $scope.setFavorites(favorites);
                            GroupService.getFollowerSize(groupId).then(function (data) {
                                $scope.summary.followerSize = data;
                            });
                            $scope.toastMe('Group ' + group.name + ' is removed from your favorites.');
                        });
                    }
                });
            };


        }
    ]);