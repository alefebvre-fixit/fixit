
angular.module('fixit').controller('EditAvailabilityCardController',
    ['ProjectService', '$scope', '$cordovaDatePicker',
        function (ProjectService, $scope, $cordovaDatePicker) {

            $scope.addProposal = function() {

                var proposal = {date:new Date()};

                if (!$scope.isPluginActivated()){
                    $scope.card.proposals.push(proposal);
                    return;
                }
                //To be removed

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

            $scope.deleteProposal = function(index){
                $scope.card.proposals.splice(index, 1);
            };


            $scope.data = {
                showReorder: false,
                showDelete: false
            };

            $scope.reorderProposal = function(proposal, fromIndex, toIndex) {

                console.log("reorderProposal fromIndex = " + fromIndex + ' toIndex=' + toIndex);

                $scope.card.proposals.splice(fromIndex, 1);
                $scope.card.proposals.splice(toIndex, 0, proposal);
            };

        }
    ]);

angular.module('fixit').controller('AvailabilityCardController',
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

                CardService.availabilities(project, card, $scope.votes).then(function (data) {
                    $scope.setProject(data);
                    $scope.toastMe(card.name + ' voted.');
                });


            };

        }
    ]);
