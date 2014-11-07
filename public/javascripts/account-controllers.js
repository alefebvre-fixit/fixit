
fixItApp.factory('SettingService', ['$http', function($http) {

    var resultService = {
        getAccount: function() {
            var promise = $http.get('/api/account').then(function (response) {
                return response.data;
            });
            return promise;
        },
        getAccountSummary: function() {
            var promise = $http.get('/api/account/summary').success(function (response) {
                return response.data;
            });
            return promise;
        },
        saveAccount: function(account) {
            var promise = $http.post('/api/account', account).then(function (response) {
                return response.data;
            });
            return promise;
        },
        signupUser: function(signupRequest) {
            console.log("Posting signupUser" + signupRequest.email);

            var promise = $http.post('/api/signup', signupRequest).then(function (response) {
                return response.data;
            });
            return promise;
        }

    };

    return resultService;
}]);


fixItApp.controller('AccountSummaryController', ['SettingService', '$scope', '$http', function (SettingService, $scope, $http) {

    $scope.init = function()
    {
        SettingService.getAccountSummary().then(function (data) {
            $scope.accountSummary = data;
        });
    };

}
]);


fixItApp.controller('EditAccountController', ['SettingService', '$scope', '$http', function (SettingService, $scope, $http) {

    $scope.init = function()
    {
        $scope.section = 'account';
        SettingService.getAccount().then(function (data) {
            $scope.account = data;
        });
    };


    $scope.saveAccount = function() {
        SettingService.saveAccount()
    }

    $scope.isActive = function (section) {
        var active = (section === $scope.section);
        return active;
    };

}
]);

fixItApp.controller('SignUpController', ['SettingService', '$scope', '$http', '$location', function (SettingService, $scope, $http, $location) {

    $scope.submitForm = function(isValid) {
        // check to make sure the form is completely valid
        if (isValid) {
            console.log("SignupForm is valid" + $scope.signupRequest.email);

            SettingService.signupUser($scope.signupRequest) .then(function (data) {
                console.log("Just signed up");
                $location.url('projects');
            });

        } else {
            console.log("SignupForm is not valid");
        }


    };

}
]);




