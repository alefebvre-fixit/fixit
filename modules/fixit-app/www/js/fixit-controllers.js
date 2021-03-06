
angular.module('fixit').controller('FixItController', ['$scope', '$rootScope', '$window', '$cordovaToast', '$state',
	function ($scope, $rootScope, $window,  $cordovaToast, $state) {
	$scope.toastMe = function(message) {

		if ($scope.isPluginActivated()){
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
	$scope.isPluginActivated = function(){
		return $rootScope.isPluginEnabled;
	};

	$scope.setUser = function(user){
		$rootScope.user = user;
		localStorage.setItem("username",user.username);
	};

	$scope.setFavorites = function(favorites){
		$rootScope.favorites = favorites;
	};

	$scope.isFavorite = function(project){
		if (project){
			console.log("isFavorite " + project.id);
			return ($rootScope.favorites.indexOf(project.id) >= 0);
		}
		return false;
	};

	$scope.isMine = function(project){
		if (project){
			if (project.username){
				return (project.username == $rootScope.user.username);
			}
			else if (project.contributor){
				return (project.contributor == $rootScope.user.username);
			}
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

	$scope.goToCard = function(projectId, cardId){
		$state.go('app.card-view', {projectId: projectId, cardId: cardId});
	};

	$scope.goToEditCard = function(projectId, cardId){
		$state.go('app.card-edit', {projectId: projectId, cardId: cardId});
	};

	$scope.goToSettings = function(){
		$state.go('app.settings');
	};

	$scope.goToFollowers = function(username){
		$state.go('app.followers', {username: username});
	};

}
]);


