angular.module('ya-app').controller('ListEventsController',
    ['EventService', '$scope',
        function (EventService, $scope) {

            EventService.getEvents().then(function (events) {
                $scope.events = events;
            });

            $scope.doRefresh = function() {
                EventService.getEvents().then(function (events) {
                    $scope.events = events;
                });
                $scope.$broadcast('scroll.refreshComplete');
            };

        }
    ]);

angular.module('ya-app').controller('ViewEventController',
    ['$scope', '$state', '$ionicPopup', '$ionicActionSheet', '$ionicModal', 'EventService', 'eventId', '$ionicModal',
        function ($scope, $state, $ionicPopup, $ionicActionSheet, $ionicModal, EventService, eventId) {
            console.log("ViewEventController eventId=" + eventId);


            $scope.summary = {followerSize : 0, commentSize : 0, comments: []};

            EventService.getEvent(eventId).then(function (event) {
                console.log("ViewEventController getEvent is called eventId=" + eventId);
                $scope.event = event;

            });


            $scope.setEvent =function(newEvent){
                $scope.event = newEvent;
                EventService.getCommentSize(eventId).then(function (data) {
                    $scope.summary.commentSize = data;
                });
                EventService.getComments(eventId).then(function (data) {
                    $scope.summary.comments = data;
                });
            };

            $scope.deleteEvent = function(eventToDelete) {
                var confirmPopup = $ionicPopup.confirm({
                    title: 'Delete Event',
                    template: 'Are you sure you want to delete this event?'
                });
                confirmPopup.then(function(res) {
                    if(res) {
                        EventService.deleteEvent(eventToDelete).then(function () {
                            $scope.toastMe('Event ' + eventToDelete.name + ' deleted.');
                        });
                    }
                });
            };

            $scope.createEvent = function(eventToDelete) {

            };


                // Triggered on a button click, or some other target
            $scope.showEventAction = function(eventToUpdate) {
                // Show the action sheet
                $ionicActionSheet.show({
                    buttons: [
                        { text: 'Edit' },
                        { text: 'Create Event' }
                    ],
                    destructiveText: 'Delete',
                    titleText: 'Event',
                    cancelText: 'Cancel',
                    destructiveButtonClicked: function() {
                        $scope.deleteEvent(eventToUpdate);
                    },
                    buttonClicked: function(index) {
                        if (index == 0){
                            $scope.openEditEvent(eventToUpdate);
                        } else if (index == 1) {
                            $scope.publishProject(eventToUpdate);
                        }
                        return true;
                    }
                });
            };

        }
    ]);