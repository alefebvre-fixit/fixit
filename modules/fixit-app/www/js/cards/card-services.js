angular.module('fixit').factory('CardService',
    ['$http', '$rootScope',
        function($http, $rootScope) {

            var resultService;
            resultService = {
                getContributions: function (projectId, cardId) {
                    return $http.get($rootScope.baseUrl + '/api/projects/' + projectId + '/cards/' + cardId + '/contributions').then(function (response) {
                        return response.data;
                    });
                }

            };

            return resultService;
        }]);


