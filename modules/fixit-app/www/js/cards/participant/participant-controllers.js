
angular.module('fixit').controller('EditParticipantCardController',
    ['ProjectService', '$scope',
        function (ProjectService, $scope) {

        }
    ]);

angular.module('fixit').controller('ParticipantCardController', ['CardService', '$scope',
    function (CardService, $scope) {

        $scope.participate = function(project, card) {
            console.log("participate projectId=" + project.id + " cardId=" + card.id);
            CardService.participate(project, card, 1).then(function (data) {
                $scope.setProject(data);
                $scope.toastMe('Thanks for participating!');
            });
        };

    }
]);