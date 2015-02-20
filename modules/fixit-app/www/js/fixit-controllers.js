
angular.module('fixit').controller('FixItController', ['$scope', '$rootScope', '$window', '$cordovaToast', '$state',
	function ($scope, $rootScope, $window,  $cordovaToast, $state) {
	$scope.toastMe = function(message) {
	    
	    $cordovaToast.show(message, 'short', 'center').then(
		    function(success) {
			// success
		    }, function(error) {
			// error
		    });
	    
	    if (window.cordova) {
		    $cordovaToast.show('Youpi', 'short', 'center').then(
			    function(success) {
				// success
			    }, function(error) {
				// error
			    });
	    }
	};

	$scope.setUser = function(user){
		$rootScope.user = user;
		localStorage.setItem("username",user.username);
	};


	$scope.getUsername = function(){
	    return $rootScope.user.username;
	};

	$scope.goToUser = function(username){
		$state.go('app.user', {username: username});
	};

	$scope.goToProject = function(projectId){
		$state.go('app.project-single', {projectId: projectId});
	};

	$scope.goToSettings = function(){
		$state.go('app.settings');
	};

	$scope.goToFollowers = function(username){
		$state.go('app.followers', {username: username});
	};
	    	  
}
]);


