
angular.module('ya-app').controller('YaController', ['$scope', '$log', '$rootScope', '$window', '$cordovaToast', '$state',
	function ($scope, $log, $rootScope, $window,  $cordovaToast, $state) {
	$scope.toastMe = function(message) {

		if ($scope.isPluginActivated()){
			$cordovaToast.show(message, 'short', 'center').then(
				function(success) {
					// success
				}, function(error) {
					// error
				});
		} else {
			$log.log(message);
		}

	};
	$scope.isPluginActivated = function(){
		return $rootScope.isPluginEnabled;
	};

	$scope.setUser = function(user){
		$log.log("setUser from controller" + user);

		$rootScope.user = user;
		localStorage.setItem("username",user.username);
	};

	$scope.setFavorites = function(favorites){
		$log.log("setFavorites from controller" + favorites);
		$rootScope.favorites = favorites;
	};

	$scope.isFavorite = function(group){
		if (group){
			$log.log("isFavorite from controller" + group.id);
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
		$state.go('ya.user', {username: username});
	};

	$scope.goToGroup = function(groupId){
		$state.go('ya.group-single', {groupId: groupId});
	};

	$scope.goToCard = function(groupId, cardId){
		$state.go('ya.card-view', {groupId: groupId, cardId: cardId});
	};

	$scope.goToEditCard = function(groupId, cardId){
		$state.go('ya.card-edit', {groupId: groupId, cardId: cardId});
	};

	$scope.goToSettings = function(){
		$state.go('ya.settings');
	};

	$scope.goToFollowers = function(username){
		$state.go('ya.followers', {username: username});
	};


}
]);


