
angular.module('ya-app').controller('EditSurveyController',
    ['EventService', '$scope', '$log', '$state', '$ionicModal', 'eventId','YaService',
        function (EventService, $scope, $log, $state, $ionicModal, eventId, YaService) {


            $scope.survey = {eventId: eventId, suggestionEnabled: false, multipleChoices: false, proposals: []};

            EventService.getEvent(eventId).then(function(event) {
                $scope.event = event;
                event.date = new Date(event.date);
            });


            $scope.saveSurvey = function(form) {
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


            //Start Modal proposal

            $ionicModal.fromTemplateUrl('templates/events/survey/survey-proposal-modal.html', {
                scope: $scope,
                animation: 'slide-in-up'
            }).then(function(modal) {
                $scope.proposalModal = modal;
            });

            $scope.createProposal = function() {
                $scope.proposal = {name: '', description: ''};
                $scope.proposalModal.show();
            };

            $scope.editProposal = function(original) {
                $scope.proposal = angular.copy(original);
                $scope.proposalModal.show();
            };

            $scope.closeProposal = function() {
                $scope.proposalModal.hide();
            };

            //Cleanup the modal when we're done with it!
            $scope.$on('$destroy', function() {
                if ($scope.proposalModal){
                    $scope.proposalModal.remove();
                }
            });

            // Execute action on hide modal
            $scope.$on('proposalModal.hidden', function() {
                // Execute action
            });

            // Execute action on remove modal
            $scope.$on('proposalModal.removed', function() {
                // Execute action
            });

            $scope.applyProposal = function(proposal) {

                $scope.survey.proposals.push(proposal);
                $scope.proposalModal.hide();
            };

            //End Modal for group edition







        }
    ]);

