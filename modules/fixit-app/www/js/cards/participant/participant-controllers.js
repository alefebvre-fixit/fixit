
angular.module('fixit').controller('EditParticipantCardController',
    ['ProjectService', '$scope',
        function (ProjectService, $scope) {

        }
    ]);

angular.module('fixit').controller('ParticipantCardController', ['CardService', '$scope', '$ionicModal',
    function (CardService, $scope, $ionicModal) {


        $ionicModal.fromTemplateUrl('templates/cards/participant/participant-card-modal.html', {
            scope: $scope,
            animation: 'slide-in-up'
        }).then(function(modal) {
            $scope.participateModal = modal;
        });

        $scope.openModal = function() {

            $scope.guestList = [];

            var arrayLength = $scope.summary.card.plusMaximumParticipant;
            for (var i = 1; i <= arrayLength; i++) {
                $scope.guestList.push({name:i, value:i});
            }

            $scope.myParticipation = {participate:true, guest:{name:0, value:0}};

            $scope.participateModal.show();
        };

        $scope.closeModal = function() {
            $scope.participateModal.hide();
        };

        //Cleanup the modal when we're done with it!
        $scope.$on('$destroy', function() {
            if ($scope.participateModal){
                $scope.participateModal.remove();
            }
        });

        // Execute action on hide modal
        $scope.$on('participateModal.hidden', function() {
            // Execute action
        });

        // Execute action on remove modal
        $scope.$on('participateModal.removed', function() {
            // Execute action
        });



        $scope.participate = function(myParticipation) {
            console.log(myParticipation);
            console.log("participate projectId=" + $scope.project.id + " cardId=" + $scope.summary.card.id);
            var participant = 0;
            if (myParticipation.participate){
                if (myParticipation.guest.value){
                    participant = 1 + myParticipation.guest.value;
                } else {
                    participant = 1;
                }
            }
            console.log("participate participant=" + participant);

            CardService.participate($scope.project, $scope.summary.card, participant).then(function (data) {
                $scope.setProject(data);
                $scope.closeModal();
                $scope.toastMe('Thanks for participating!');
            });


        };

    }
]);