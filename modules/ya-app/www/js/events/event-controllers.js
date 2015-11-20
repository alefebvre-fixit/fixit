angular.module('ya-app').controller('ListEventsController',
    ['EventService', '$scope',
        function (EventService, $scope) {

            $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
                EventService.getEvents().then(function (events) {
                    $scope.events = events;
                });
            });

            $scope.doRefresh = function() {
                EventService.getEvents().then(function (events) {
                    $scope.events = events;
                    $scope.$broadcast('scroll.refreshComplete');
                });
            };

        }
    ]);

angular.module('ya-app').controller('ParticipationListController',
    ['EventService', '$scope', 'eventId',
        function (EventService, $scope, eventId) {

            $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
                EventService.getEventParticipations(eventId).then(function (participations) {
                    $scope.participations = participations;
                });
            });

            $scope.doRefresh = function() {
                EventService.getEventParticipations(eventId).then(function (participations) {
                    $scope.participations = participations;
                    $scope.$broadcast('scroll.refreshComplete');
                });
            };
        }
    ]);



angular.module('ya-app').controller('EditEventController',
    ['EventService', '$scope', '$log', '$state', 'eventId', '$cordovaDatePicker',
        function (EventService, $scope, $log, $state, eventId, $cordovaDatePicker) {

            $scope.picker = {date: new Date() , time: new Date()};
            console.log($scope.picker.time.getUTCMinutes());

            EventService.getEvent(eventId).then(function(event) {
                $scope.event = event;
                $scope.picker = {date: event.date , time: event.date};
            });

            $scope.timePickerObject = {
                inputEpochTime: (($scope.picker.time.getUTCHours() * 3600 + $scope.picker.time.getUTCMinutes() * 60 + $scope.picker.time.getUTCSeconds())),  //Optional
                step: 15,  //Optional
                format: 12,  //Optional
                titleLabel: '12-hour Format',  //Optional
                setLabel: 'Set',  //Optional
                closeLabel: 'Close',  //Optional
                setButtonType: 'button-calm',  //Optional
                closeButtonType: 'button-stable',  //Optional
                callback: function (val) {    //Mandatory
                    timePickerCallback(val);
                }
            };

            function timePickerCallback(val) {
                if (typeof (val) === 'undefined') {
                    console.log('Time not selected');
                } else {
                    $scope.picker.time = new Date(val * 1000);
                    console.log('Selected epoch is : ', val, 'and the time is ', $scope.picker.time.getUTCHours(), ':', $scope.picker.time.getUTCMinutes(), 'in UTC');
                }
            }


            $scope.datepickerObject = {
                titleLabel: 'Title',  //Optional
                todayLabel: 'Today',  //Optional
                closeLabel: 'Close',  //Optional
                setLabel: 'Set',  //Optional
                setButtonType : 'button-calm',  //Optional
                todayButtonType : 'button-stable',  //Optional
                closeButtonType : 'button-stable',  //Optional
                inputDate: $scope.picker.date,  //Optional
                mondayFirst: true,  //Optional
                templateType: 'popup', //Optional
                showTodayButton: 'true', //Optional
                modalHeaderColor: 'bar-calm', //Optional
                modalFooterColor: 'bar-calm', //Optional
                from: new Date(2015, 1, 1), //Optional
                to: new Date(2017, 1, 1),  //Optional
                callback: function (val) {  //Mandatory
                    datePickerCallback(val);
                },
                dateFormat: 'dd-MM-yyyy', //Optional
                closeOnSelect: false //Optional
            };

            datePickerCallback = function(val){
                if (typeof (val) === 'undefined') {
                    console.log('Date not selected');
                } else {
                    $scope.picker.date = val;
                }
            };


            $scope.saveEvent = function(form) {

                // If form is invalid, return and let AngularJS show validation errors.
                if (form.$invalid) {
                    return;
                }

                $log.log(event);
                EventService.saveEvent($scope.event).then(function(data){
                    $state.go('event', {eventId: data.id});
                });
            };


        }
    ]);

angular.module('ya-app').controller('CreateEventController',
    ['EventService', '$scope', '$log', '$state','$ionicHistory', 'groupId',
        function (EventService, $scope, $log, $state, $ionicHistory, groupId) {

            $scope.event = {groupId: groupId};

            EventService.instanciateEvent(groupId).then(function(data){
                $scope.event = data;
            });

            $scope.saveEvent = function(form) {

                // If form is invalid, return and let AngularJS show validation errors.
                if (form.$invalid) {
                    return;
                }

                $log.log(event);
                EventService.saveEvent($scope.event).then(function(event){
                    $ionicHistory.currentView($ionicHistory.backView());
                    $state.go('event', {eventId: event.id, location: 'replace'});
                });
            };


        }
    ]);

angular.module('ya-app').controller('ViewEventController',
    ['$scope', '$state', '$log', '$ionicPopup', '$ionicActionSheet', '$ionicModal', '$ionicPopover', 'YaService', 'EventService', 'eventId',
        function ($scope, $state, $log, $ionicPopup, $ionicActionSheet, $ionicModal, $ionicPopover,  YaService, EventService, eventId) {

            $log.log("ViewEventController eventId=" + eventId);

            $scope.summary = {participationsSize : '-', commentSize : '-', comments: [], lastParticipations: [],  myParticipation : {}};

            $scope.reload = function(eventId){
                EventService.getEvent(eventId).then(function(event) {
                    $log.log("ViewEventController getEvent is called eventId=" + eventId);
                    $scope.event = event;

                    EventService.getUserParticipation(event).then(function(participation) {
                        $log.log("summary.myParticipation=" + participation.status);
                        $scope.summary.myParticipation = participation;
                    });

                    EventService.getParticipationsSize(event).then(function(size) {
                        $log.log("summary.participationSize=" + size);
                        $scope.summary.participationsSize = size;
                    });

                    EventService.getLastParticipations(event).then(function(participations) {
                        $log.log("summary.lastParticipations=" + participations);
                        $scope.summary.lastParticipations = participations;
                    });

                    EventService.getCommentSize(event).then(function(size) {
                        $log.log("summary.getCommentSize=" + size);
                        $scope.summary.commentSize = size;
                    });


                });
            };

            //To insure the back button is displayed
            $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
                viewData.enableBack = true;
                $scope.reload(eventId);
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
                $scope.closePopover();

                var confirmPopup = $ionicPopup.confirm({
                    title: 'Delete Event',
                    template: 'Are you sure you want to delete this event?',
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
                        EventService.deleteEvent(eventToDelete).then(function () {
                            YaService.toastMe('Event ' + eventToDelete.name + ' deleted.');
                            $state.go('tabs.events');
                        });
                    }
                });
            };

            $scope.openEditEvent = function(event){
                $scope.closePopover();
                $state.go("event-edit", { eventId: event.id});
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
                $log.log("$scope.participation.value=" + $scope.rsvp.participation);

                if (rsvp.participation){
                    rsvp.status = 'IN';
                } else {
                    rsvp.status = 'OUT';
                }
                delete rsvp.participation;

                EventService.participate(rsvp).then(function(data){
                    $log.log("rsvp data=");
                    $log.log(data);
                    $scope.summary.myParticipation = data;
                    $scope.closeRSVP();
                    YaService.toastMe('You are now ' + data.status);
                    $scope.reload(rsvp.eventId);
                });
            };

            //End Modal for group edition


            $ionicPopover.fromTemplateUrl('templates/events/partial/event-popover.html', {
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

angular.module('ya-app').controller('EventCommentsController', ['EventService', '$scope', 'eventId',
    function (EventService, $scope, eventId) {

        console.log('enter EventCommentsController');

        $scope.comment = {eventId: eventId, content: ''};


        $scope.$on('$ionicView.beforeEnter', function (event, viewData) {
            EventService.getComments(eventId).then(function (comments) {
                $scope.comments = comments;
            });
        });


        $scope.postComment = function(comment) {
            EventService.postComment(comment.eventId, comment.content).then(function (comment) {
                $scope.comments.push(comment);
                $scope.comment = {eventId: eventId, content: ''};
            });
        };





    }
]);