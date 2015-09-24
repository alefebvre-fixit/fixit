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

angular.module('ya-app').controller('ParticipationListController',
    ['EventService', '$scope', 'eventId',
        function (EventService, $scope, eventId) {

            EventService.getEventParticipations(eventId).then(function (participations) {
                $scope.participations = participations;
            });

            $scope.doRefresh = function() {
                EventService.getEventParticipations(eventId).then(function (participations) {
                    $scope.participations = participations;
                });
                $scope.$broadcast('scroll.refreshComplete');
            };

        }
    ]);


angular.module('ya-app').controller('ViewEventController',
    ['$scope', '$state', '$ionicPopup', '$ionicActionSheet', '$ionicModal', 'YaService', 'EventService', 'eventId', '$ionicModal',
        function ($scope, $state, $ionicPopup, $ionicActionSheet, $ionicModal, YaService, EventService, eventId) {
            console.log("ViewEventController eventId=" + eventId);

            //To insure the back button is displayed
            $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
                viewData.enableBack = true;
            });

            $scope.summary = {participationsSize : '-', commentSize : '-', comments: [], lastParticipations: [],  myParticipation : {}};

            $scope.reload = function(eventId){
                EventService.getEvent(eventId).then(function(event) {
                    console.log("ViewEventController getEvent is called eventId=" + eventId);
                    $scope.event = event;

                    EventService.getUserParticipation(event).then(function(participation) {
                        console.log("summary.myParticipation=" + participation.status);
                        $scope.summary.myParticipation = participation;
                    });

                    EventService.getParticipationsSize(event).then(function(size) {
                        console.log("summary.participationSize=" + size);
                        $scope.summary.participationsSize = size;
                    });

                    EventService.getLastParticipations(event).then(function(participations) {
                        console.log("summary.lastParticipations=" + participations);
                        $scope.summary.lastParticipations = participations;
                    });
                });
            };

            $scope.reload(eventId);

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
                            YaService.toastMe('Event ' + eventToDelete.name + ' deleted.');
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


            //Start Modal RSVP

            $ionicModal.fromTemplateUrl('templates/events/partial/event-participation-modal.html', {
                scope: $scope,
                animation: 'slide-in-up'
            }).then(function(modal) {
                $scope.editRSVP = modal;
            });

            $scope.openRSVP = function(rsvpToUpdate) {
                $scope.rsvp = angular.copy(rsvpToUpdate);

                if (rsvpToUpdate.status == 'OUT'){
                    $scope.rsvp.participation = false;
                } else {
                    $scope.rsvp.participation = true;
                }
                $scope.editRSVP.show();
            };

            $scope.closeRSVP = function() {
                $scope.editRSVP.hide();
            };

            //Cleanup the modal when we're done with it!
            $scope.$on('$destroy', function() {
                if ($scope.editRSVP){
                    $scope.editRSVP.remove();
                }
            });

            // Execute action on hide modal
            $scope.$on('editRSVP.hidden', function() {
                // Execute action
            });

            // Execute action on remove modal
            $scope.$on('editRSVP.removed', function() {
                // Execute action
            });

            $scope.saveRSVP = function(rsvp) {
                console.log("$scope.participation.value=" + $scope.rsvp.participation);

                if (rsvp.participation){
                    rsvp.status = 'IN';
                } else {
                    rsvp.status = 'OUT';
                }
                delete rsvp.participation;

                EventService.participate(rsvp).then(function(data){
                    console.log("rsvp data=");
                    console.log(data);
                    $scope.summary.myParticipation = data;
                    $scope.closeRSVP();
                    YaService.toastMe('You are now ' + data.status);
                    $scope.reload(rsvp.eventId);
                });
            };

            //End Modal for group edition


        }
    ]);