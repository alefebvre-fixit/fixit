angular.module('fixit').factory('ProjectService',
    ['$http', '$rootScope',
        function($http, $rootScope) {

            var resultService;
            resultService = {
                getProjects: function () {
                    return $http.get($rootScope.baseUrl + '/api/projects').then(function (response) {
                        return response.data;
                    });
                },
                getProjectsByOwner: function (username) {
                    return $http.get($rootScope.baseUrl + '/api/users/' + username + '/projects').then(function (response) {
                        return response.data;
                    });
                },
                getProject: function (projectId) {
                    return $http.get($rootScope.baseUrl + '/api/projects/' + projectId).then(function (response) {
                        return response.data;
                    });
                },
                saveProject: function (project) {
                    return $http.post($rootScope.baseUrl + '/api/projects', project).then(function (response) {
                        return response.data;
                    });
                },
                publishProject: function (project) {
                    return $http.post($rootScope.baseUrl + '/api/projects/' + project.id + '/publish').then(function (response) {
                        return response.data;
                    });
                },
                followProject: function (project) {
                    return $http.post($rootScope.baseUrl + '/api/projects/' + project.id + '/follow').then(function (response) {
                        return response.data;
                    });
                },
                unfollowProject: function (project) {
                    return $http.post($rootScope.baseUrl + '/api/projects/' + project.id + '/unfollow').then(function (response) {
                        return response.data;
                    });
                },
                deleteProject: function (project) {
                    return $http.post($rootScope.baseUrl + '/api/projects/' + project.id + '/delete').then(function (response) {
                        return response.data;
                    });
                },
                instanciateProject: function () {
                    return $http.get($rootScope.baseUrl + '/api/projects/new').then(function (response) {
                        return response.data;
                    });
                },
                getCard: function (projectId, cardId) {
                    return $http.get($rootScope.baseUrl + '/api/projects/' + projectId + '/cards/' + cardId).then(function (response) {
                        return response.data;
                    });
                },
                addCard: function (projectId, card) {
                    return $http.post($rootScope.baseUrl + '/api/projects/' + projectId + '/cards', card).then(function (response) {
                        return response.data;
                    });
                },
                updateCard: function (projectId, card) {
                    return $http.put($rootScope.baseUrl + '/api/projects/' + projectId + '/cards/' + card.id, card).then(function (response) {
                        return response.data;
                    });
                },
                deleteCard: function (projectId, card) {
                    return $http.delete($rootScope.baseUrl + '/api/projects/' + projectId + '/cards/' + card.id).then(function (response) {
                        return response.data;
                    });
                }
            };

            return resultService;
        }]);


