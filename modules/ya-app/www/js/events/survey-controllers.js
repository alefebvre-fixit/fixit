
angular.module('ya-app').controller('EditSurveyController',
    ['EventService', '$scope', '$log', '$state', 'eventId','YaService',
        function (EventService, $scope, $log, $state, eventId, YaService) {


            $scope.survey = {eventId: eventId, suggestionEnabled: false, multipleChoices: false, choices: []};

            EventService.getEvent(eventId).then(function(event) {
                $scope.event = event;
                event.date = new Date(event.date);
            });

            $scope.addChoice = function(){
                $log.log('addChoice');
                var choice = {name: 'test'};

                $scope.survey.choices.push(choice);
            };


            $scope.saveEvent = function(form) {
                // If form is invalid, return and let AngularJS show validation errors.
                if (form.$invalid) {
                    return;
                }
                YaService.startLoading();
                EventService.saveEvent($scope.event).then(function(data){
                    YaService.stopLoading();
                    $state.go('event', {eventId: data.id});
                });

            };


        }
    ]);

