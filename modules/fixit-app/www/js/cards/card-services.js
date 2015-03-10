angular.module('fixit').factory('CardService',
    ['$http', '$rootScope',
        function($http, $rootScope) {

            var resultService;
            resultService = {
                instanciateContribution: function (project, card, type) {
                    return $http.get($rootScope.baseUrl + '/api/projects/' + project.id + '/cards/' + card.id + '/contributions/new/' + type).then(function (response) {
                        return response.data;
                    });
                },
                getContributions: function (projectId, cardId) {
                    return $http.get($rootScope.baseUrl + '/api/projects/' + projectId + '/cards/' + cardId + '/contributions').then(function (response) {
                        return response.data;
                    });
                },
                getCardSummary: function (projectId, cardId) {
                    return $http.get($rootScope.baseUrl + '/api/projects/' + projectId + '/cards/' + cardId + '/summary').then(function (response) {
                        return response.data;
                    });
                },
                provide: function (project, card, quantity) {
                    return resultService.instanciateContribution(project, card, 'item').then(function (contribution) {
                        contribution.quantityProvided = quantity;
                        return $http.post($rootScope.baseUrl + '/api/projects/' + project.id + '/cards/' + card.id + '/contributions', contribution).then(function (response) {
                            return response.data;
                        });
                    });
                },
                availabilities: function (project, card, ids) {
                    return resultService.instanciateContribution(project, card, 'date').then(function (contribution) {
                        contribution.votes = ids;
                        return $http.post($rootScope.baseUrl + '/api/projects/' + project.id + '/cards/' + card.id + '/contributions', contribution).then(function (response) {
                            return response.data;
                        });
                    });
                },
                vote: function (project, card, ids) {
                    return resultService.instanciateContribution(project, card, 'survey').then(function (contribution) {
                        contribution.votes = ids;
                        return $http.post($rootScope.baseUrl + '/api/projects/' + project.id + '/cards/' + card.id + '/contributions', contribution).then(function (response) {
                            return response.data;
                        });
                    });
                },
                participate: function (project, card, participants) {
                    return resultService.instanciateContribution(project, card, 'participant').then(function (contribution) {
                        contribution.participants = participants;
                        return $http.post($rootScope.baseUrl + '/api/projects/' + project.id + '/cards/' + card.id + '/contributions', contribution).then(function (response) {
                            return response.data;
                        });
                    });
                },
                cancelContribution: function (project, contribution) {
                    return $http.post($rootScope.baseUrl + '/api/projects/' + project.id + '/contributions/' + contribution.id + '/cancel').then(function (response) {
                        return response.data;
                    });
                },
                instanciateCard: function (project, type) {
                    return $http.get($rootScope.baseUrl + '/api/projects/' + project.id + '/cards/new/' + type).then(function (response) {
                        return response.data;
                    });
                }
            };

            return resultService;
        }]);
