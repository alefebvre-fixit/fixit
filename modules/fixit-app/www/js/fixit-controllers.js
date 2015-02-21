
angular.module('fixit').controller('FixItController', ['$scope', '$rootScope', '$window', '$cordovaToast', '$state',
	function ($scope, $rootScope, $window,  $cordovaToast, $state) {
	$scope.toastMe = function(message) {

		if ($rootScope.isToastEnabled){
			$cordovaToast.show(message, 'short', 'center').then(
				function(success) {
					// success
				}, function(error) {
					// error
				});
		} else {
			console.log(message);
		}

	};


	$scope.setUser = function(user){
		$rootScope.user = user;
		localStorage.setItem("username",user.username);
	};

	$scope.setFavorites = function(favorites){
		$rootScope.favorites = favorites;
	};

	$scope.isFavorite = function(project){
		return ($rootScope.favorites.indexOf(project.id) > 0);
	};

	$scope.isMine = function(project){
		if (project.username){
			return (project.username == $rootScope.user.username);
		}
		else if (project.contributor){
			return (project.contributor == $rootScope.user.username);
		}
		return false;
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


