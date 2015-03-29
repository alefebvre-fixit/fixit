
angular.module('fixit').controller('EditSurveyCardController',
    ['ProjectService', '$scope', '$ionicModal',
        function (ProjectService, $scope, $ionicModal) {

            $ionicModal.fromTemplateUrl('templates/edit-proposal.html', {
                scope: $scope,
                animation: 'slide-in-up'
            }).then(function(modal) {
                $scope.modal = modal;
            });

            $scope.openModal = function() {
                $scope.modal.show();
            };

            $scope.closeModal = function() {
                $scope.modal.hide();
            };

            //Cleanup the modal when we're done with it!
            $scope.$on('$destroy', function() {
                if (modal){
                    $scope.modal.remove();
                }
            });

            // Execute action on hide modal
            $scope.$on('modal.hidden', function() {
                // Execute action
            });

            // Execute action on remove modal
            $scope.$on('modal.removed', function() {
                // Execute action
            });

            $scope.deleteProposal = function(index){
                $scope.card.proposals.splice(index, 1);
            };

            $scope.addProposal = function(proposal) {
                $scope.card.proposals.push(proposal);
                $scope.modal.hide();
            };


            $scope.data = {
                showReorder: false,
                showDelete: false
            };

            $scope.reorderProposal = function(proposal, fromIndex, toIndex) {

                console.log("reorderProposal fromIndex = " + fromIndex + ' toIndex=' + toIndex);

                $scope.card.proposals.splice(fromIndex, 1);
                $scope.card.proposals.splice(toIndex, 0, proposal);
            };

        }
    ]);

angular.module('fixit').controller('SurveyCardController',
    ['CardService', '$scope',
        function (CardService, $scope) {

            $scope.votes = [];

            $scope.vote = function(project, card) {

                var arrayLength = card.proposals.length;
                for (var i = 0; i < arrayLength; i++) {
                    if (card.proposals[i].selected){
                        $scope.votes.push(card.proposals[i].id);
                    }
                }

                CardService.vote(project, card, $scope.votes).then(function (data) {
                    $scope.setProject(data);
                    $scope.toastMe(card.name + ' voted.');
                });

            };

        }
    ]);

