angular.module('fixit').controller('TesterController', ['$scope', '$rootScope', '$cordovaToast', '$cordovaDatePicker',
                                                             function ($scope, $rootScope, $cordovaToast, $cordovaDatePicker) {

    $scope.callToast = function() {
	    console.log('Calling callToast');
	    $cordovaToast.show('Hello', 'long', 'center').then(
		    function(success) {
			// success
		    }, function(error) {
			// error
		    });
    }
    
    $scope.callDatePicker = function() {
	    console.log('Calling callDatePicker');

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
		        alert(date);
		    });

		  }, false);
}

}
]);



