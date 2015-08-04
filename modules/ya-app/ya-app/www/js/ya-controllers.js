
angular.module('ya-app').controller('YaController', ['$scope', '$rootScope', '$window', '$cordovaToast', '$state',
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

	$scope.isFavorite = function(group){
		if (group){
			console.log("isFavorite " + group.id);
			return ($rootScope.favorites.indexOf(group.id) >= 0);
		}
		return false;
	};

	$scope.isMine = function(group){
		if (group){
			if (group.username){
				return (group.username == $rootScope.user.username);
			}
			else if (group.contributor){
				return (group.contributor == $rootScope.user.username);
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

	$scope.goToGroup = function(groupId){
		$state.go('app.group-single', {groupId: groupId});
	};

	$scope.goToCard = function(groupId, cardId){
		$state.go('app.card-view', {groupId: groupId, cardId: cardId});
	};

	$scope.goToEditCard = function(groupId, cardId){
		$state.go('app.card-edit', {groupId: groupId, cardId: cardId});
	};

	$scope.goToSettings = function(){
		$state.go('app.settings');
	};

	$scope.goToFollowers = function(username){
		$state.go('app.followers', {username: username});
	};

}
]);


