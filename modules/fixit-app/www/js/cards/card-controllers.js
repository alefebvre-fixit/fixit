angular.module('fixit').controller('CardSelectorController',
    ['ProjectService', '$scope', 'project',
        function (ProjectService, $scope, project) {
            console.log('Calling CardSelectorController');

            $scope.project = project;

        }
    ]);

angular.module('fixit').controller('CardController',
    ['$scope', 'project', 'summary', 'CardService',
        function ($scope, project, summary, CardService) {

            $scope.project = project;
            $scope.summary = summary;

            $scope.setProject =function(newProject){
                $scope.project = newProject;

                CardService.getCardSummary(project.id, $scope.summary.card.id).then(function (data) {
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
                    { text: 'Update' }
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

angular.module('fixit').controller('EditDateCardController',
    ['ProjectService', '$scope', '$cordovaDatePicker',
        function (ProjectService, $scope, $cordovaDatePicker) {

            $scope.addDateProposal = function() {

                var proposal = {date:new Date()};
                //To be removed
                $scope.card.proposals.push(proposal);

                var options = {
                    date: new Date(),
                    mode: 'date', // or 'time'
                    minDate: new Date() - 10000,
                    allowOldDates: true,
                    allowFutureDates: false,
                    doneButtonLabel: 'DONE',
                    doneButtonColor: '#F2F3F4',
                    cancelButtonLabel: 'CANCEL',
                    cancelButtonColor: '#000000'
                };

                document.addEventListener("deviceready", function () {

                    $cordovaDatePicker.show(options).then(function(date){
                        var newProposal = {date:new Date()};
                        newProposal.date = date;
                        $scope.card.proposals.push(newProposal);
                    });

                }, false);
            };



        }
    ]);

angular.module('fixit').controller('DateCardController',
    ['ProjectService', '$scope',
        function (ProjectService, $scope) {

            $scope.votes = [];

            $scope.vote = function(project, card) {

                var arrayLength = card.proposals.length;
                for (var i = 0; i < arrayLength; i++) {
                    if (card.proposals[i].selected){
                        $scope.votes.push(card.proposals[i].id);
                    }
                }

                ProjectService.vote(project, card, $scope.votes).then(function (data) {
                    $scope.setProject(data);
                    $scope.toastMe(card.name + ' voted.');
                });


            };


        }
    ]);

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