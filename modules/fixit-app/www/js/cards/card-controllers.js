angular.module('fixit').controller('CardSelectorController',
    ['ProjectService', '$scope', 'project',
        function (ProjectService, $scope, project) {
            console.log('Calling CardSelectorController');

            $scope.project = project;

        }
    ]);

angular.module('fixit').controller('CardController',
    ['$scope', 'projectId', 'cardId', 'CardService', 'ProjectService',
        function ($scope, projectId, cardId, CardService, ProjectService) {

            ProjectService.getProject(projectId).then(function (data) {
                $scope.project = data;
            });
            CardService.getCardSummary(projectId, cardId).then(function (data) {
                $scope.summary = data;
            });

            $scope.setProject =function(project){
                $scope.project = project;
                console.log("setProject=" + project.id);
                CardService.getCardSummary(project.id, $scope.summary.card.id).then(function (data) {
                    console.log("getCardSummary=" + project.id + " cardId=" + $scope.summary.card.id);
                    $scope.summary = data;
                });
            };

            $scope.cancelContribution = function(project, contribution) {
                CardService.cancelContribution(project, contribution).then(function (data) {
                    $scope.setProject(data);
                    $scope.toastMe('Participation canceled.');
                });
            };

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

angular.module('fixit').controller('EditCardController', ['ProjectService',
    '$scope',
    '$state',
    '$ionicPopup',
    '$ionicActionSheet',
    'project',
    'card',
    function (ProjectService, $scope, $state, $ionicPopup, $ionicActionSheet, project, card) {

        $scope.project = project;

        //We must convert string to date. Put that code somewhere else
        if (card.date){
            card.date = new Date(card.date);
        }
        if (card.proposals){
            var arrayLength = card.proposals.length;
            for (var i = 0; i < arrayLength; i++) {
                if (card.proposals[i].date){
                    card.proposals[i].date = new Date(card.proposals[i].date);
                }
            }
        }


        $scope.card = card;

        $scope.addCard = function(projectId, card) {
            ProjectService.addCard(projectId, card).then(function () {
                $state.go('app.project-edit', {projectId: project.id});
            });
        };

        // A confirm dialog
        $scope.deleteCard = function(projectId, card) {
            var confirmPopup = $ionicPopup.confirm({
                title: 'Delete Card',
                template: 'Are you sure you want to delete this card?'
            });
            confirmPopup.then(function(res) {
                if(res) {
                    ProjectService.deleteCard(projectId, card).then(function () {
                        $state.go('app.project-edit', {projectId: project.id});
                    });
                }
            });
        };


        // Triggered on a button click, or some other target
        $scope.showAction = function(project, card) {
            // Show the action sheet
            $ionicActionSheet.show({
                buttons: [
                    { text: 'Apply' }
                ],
                destructiveText: 'Delete',
                titleText: 'Update your card',
                cancelText: 'Cancel',
                destructiveButtonClicked: function() {
                    $scope.deleteCard(project.id, card);
                },
                buttonClicked: function(index) {
                    if (index == 0){
                        $scope.addCard(project.id, card);
                    }
                    return true;
                }
            });
        };


    }
]);