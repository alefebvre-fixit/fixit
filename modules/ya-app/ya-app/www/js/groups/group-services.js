angular.module('ya-app').factory('GroupService',
    ['$http', '$rootScope',
        function($http, $rootScope) {

            var resultService;
            resultService = {
                getGroups: function () {
                    return $http.get($rootScope.baseUrl + '/api/groups').then(function (response) {
                        return response.data;
                    });
                },
                getGroupsByOwner: function (username) {
                    return $http.get($rootScope.baseUrl + '/api/users/' + username + '/groups').then(function (response) {
                        return response.data;
                    });
                },
                getGroup: function (groupId) {
                    return $http.get($rootScope.baseUrl + '/api/groups/' + groupId).then(function (response) {
                        return response.data;
                    });
                },
                saveGroup: function (group) {
                    return $http.post($rootScope.baseUrl + '/api/groups', group).then(function (response) {
                        return response.data;
                    });
                },
                followGroup: function (group) {
                    return $http.post($rootScope.baseUrl + '/api/groups/' + group.id + '/follow').then(function (response) {
                        return response.data;
                    });
                },
                getFollowerSize: function (groupId) {
                    return $http.get($rootScope.baseUrl + '/api/groups/' + groupId + '/followers/size').then(function (response) {
                        return response.data;
                    });
                },
                getFollowers: function (groupId) {
                    return $http.get($rootScope.baseUrl + '/api/groups/' + groupId + '/followers').then(function (response) {
                        return response.data;
                    });
                },
                getCommentSize: function (groupId) {
                    return $http.get($rootScope.baseUrl + '/api/groups/' + groupId + '/comments/size').then(function (response) {
                        return response.data;
                    });
                },
                getComments: function (groupId) {
                    return $http.get($rootScope.baseUrl + '/api/groups/' + groupId + '/comments').then(function (response) {
                        return response.data;
                    });
                },
                postComment: function (groupId, content) {
                    return $http.post($rootScope.baseUrl + '/api/groups/' + groupId + '/comments/' + content).then(function (response) {
                        return response.data;
                    });
                },
                unfollowGroup: function (group) {
                    return $http.post($rootScope.baseUrl + '/api/groups/' + group.id + '/unfollow').then(function (response) {
                        return response.data;
                    });
                },
                deleteGroup: function (group) {
                    return $http.post($rootScope.baseUrl + '/api/groups/' + group.id + '/delete').then(function (response) {
                        return response.data;
                    });
                },
                instanciateGroup: function () {
                    return $http.get($rootScope.baseUrl + '/api/groups/new').then(function (response) {
                        return response.data;
                    });
                }
            };

            return resultService;
        }]);


