angular.module('fixit').controller('CardSelectorController',
    ['ProjectService', '$scope', 'project',
        function (ProjectService, $scope, project) {
            console.log('Calling CardSelectorController');

            $scope.project = project;

        }
    ]);


angular.module('fixit').controller('CardController',
    ['$scope', 'card', 'project', '$filter',
        function ($scope, card, project, $filter) {

            $scope.card = card;
            $scope.project = project;

            $scope.setProject =function(newProject){
                $scope.project = newProject;
                var cards = newProject.cards;
                for (index = 0; index < cards.length; ++index) {
                    if (cards[index].id == $scope.card.id){
                        $scope.card = cards[index];
                    }
                }
            };


            $scope.hasMyContribution=function(card){
                var result = $filter('MyContributions')(card,$scope.user.username);
                return (result && result.length>0);
            };

            $scope.hasOtherContribution =function(card){
                var result = $filter('OtherContributions')(card,$scope.user.username);
                return (result && result.length>0);
            };
        }
    ]);

angular.module('fixit').controller('ItemCardController', ['ProjectService', '$scope',
    function (ProjectService, $scope) {

        $scope.provide = function(project, card) {
            console.log("Provide an item projectId=" + project.id + " cardId=" + card.id);
            ProjectService.provide(project, card, 1).then(function (data) {
                $scope.setProject(data);
                $scope.toastMe(card.name + ' provided.');
            });

        };

        $scope.cancelContribution = function(project, contribution) {
            ProjectService.cancelContribution(project, contribution).then(function (data) {
                $scope.setProject(data);
                $scope.toastMe('Contribution canceled.');
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

            $scope.isOpenForContribution = function(card) {
                if  (card.open){
                    var arrayLength = card.proposals.length;
                    for (var i = 0; i < arrayLength; i++) {
                        var subArrayLength = Object.keys(card.proposals[i].contributions).length;
                        for (var j = 0; j < subArrayLength; j++) {
                            var contribution = card.proposals[i].contributions[j];
                            if (contribution.status != 'Canceled' && $scope.isMine(contribution)){
                                return false;
                            }
                        }
                    }
                }
                return true;
            };

            $scope.cancelContribution = function(project, contribution) {
                ProjectService.cancelContribution(project, contribution).then(function (data) {
                    $scope.setProject(data);
                });
            };

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