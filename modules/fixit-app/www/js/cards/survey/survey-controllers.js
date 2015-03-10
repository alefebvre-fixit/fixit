
angular.module('fixit').controller('EditSurveyCardController',
    ['ProjectService', '$scope',
        function (ProjectService, $scope) {

            var proposalName = 1;

            $scope.addDateProposal = function() {
                var proposal = {name:proposalName++};
                $scope.card.proposals.push(proposal);
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

