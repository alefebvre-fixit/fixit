
angular.module('ya-app').controller('YaController', ['$scope', '$log', '$rootScope', '$window', '$state', '$ionicPopover', 'YaService',
	function ($scope, $log, $rootScope, $window, $state, $ionicPopover, YaService) {

		$log.log('Going through YaController');

		$scope.signout = function(){
			closePopover();
			$scope.setUser(null);
            $state.go('sign-in');
		};

        $scope.goToUser = function(){
            closePopover();
            $state.go('user', {username : YaService.getUsername()});
        };

        $ionicPopover.fromTemplateUrl('templates/users/partial/main-popover.html', {
            scope: $scope
        }).then(function(popover) {
            $scope.popover = popover;
        });

        var closePopover = function() {
            if ($scope.popover){
                $scope.popover.hide();
            }
        };

        //Cleanup the popover when we're done with it!
        $scope.$on('$destroy', function() {
            $scope.popover.remove();
        });

        $scope.setUser = function(user){
			YaService.setUser(user);
		}


}
]);


