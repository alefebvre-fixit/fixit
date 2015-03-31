
angular.module('fixit').controller('MoneyCardController', ['CardService', '$scope',
    function (CardService, $scope) {

        $scope.provide = function(project, card) {
            console.log("Provide an item projectId=" + project.id + " cardId=" + card.id);
            CardService.provide(project, card, 1).then(function (data) {
                $scope.setProject(data);
                $scope.toastMe(card.name + ' provided.');
            });
        };

    }
]);
