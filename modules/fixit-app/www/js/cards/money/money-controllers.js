
angular.module('fixit').controller('MoneyCardController', ['CardService', '$scope',
    function (CardService, $scope) {

        $scope.donate = function(project, card) {
            console.log("Donate for projectId=" + project.id + " cardId=" + card.id);
            CardService.donate(project, card, 1).then(function (data) {
                $scope.setProject(data);
                $scope.toastMe(card.name + ' provided.');
            });
        };

    }
]);
