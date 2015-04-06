angular.module('fixit').controller('EditItemCardController',
    ['ProjectService', '$scope',
        function (ProjectService, $scope) {

        }
    ]);


angular.module('fixit').controller('ItemCardController', ['CardService', '$scope',
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
