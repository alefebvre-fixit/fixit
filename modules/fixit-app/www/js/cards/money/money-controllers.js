
angular.module('fixit').controller('EditMoneyCardController', ['CardService', '$scope',
    function (CardService, $scope) {

        $scope.increments = ['1', '5', '10', '20', '50'];




    }
]);



angular.module('fixit').controller('MoneyCardController', ['CardService', '$scope', '$ionicModal',
    function (CardService, $scope, $ionicModal) {

        $ionicModal.fromTemplateUrl('templates/cards/money/money-card-modal.html', {
            scope: $scope,
            animation: 'slide-in-up'
        }).then(function(modal) {
            $scope.donationModal = modal;
        });

        $scope.openModal = function() {

            $scope.myDonation = {amount: '1'};
            $scope.myDonation.amount = $scope.summary.card.increment;

            $scope.donationModal.show();
        };

        $scope.closeModal = function() {
            $scope.donationModal.hide();
        };

        //Cleanup the modal when we're done with it!
        $scope.$on('$destroy', function() {
            if ($scope.donationModal){
                $scope.donationModal.remove();
            }
        });

        // Execute action on hide modal
        $scope.$on('donationModal.hidden', function() {
            // Execute action
        });

        // Execute action on remove modal
        $scope.$on('donationModal.removed', function() {
            // Execute action
        });

        $scope.more = function(donation) {
            donation.amount += $scope.summary.card.increment;
        };

        $scope.donate = function(donation) {
            CardService.donate($scope.project, $scope.summary.card, donation.amount).then(function (data) {
                $scope.setProject(data);
                $scope.toastMe('Thanks for your donation!');
                $scope.closeModal();
            });
        };

    }
]);