(function () {
    'use strict';

    function config($stateProvider) {
        $stateProvider
            .state('root.config', {
                url:"/config",
                templateUrl: 'templates/config/config.tpl.html',
                controller: 'ConfigCtrl'
            })
    }

    function configCtrl($log, $scope,$uibModal,ConfigService) {
        $log.debug("welcome config ctrl");
        $scope.paramQuery = angular.copy(Settings.PAGE);

        var search = function () {
            ConfigService.queryConfig($scope.paramQuery, function (response) {
                $scope.configList = response.model.rows;
                $scope.paramQuery.pageIndex = response.model.pageInfo.pageIndex;
                $scope.paramQuery.pageCount = response.model.pageInfo.pageCount;
            });
        };

        search();


        $scope.pageChanged = function () {
            search();
        };



    }

    angular.module('config', ['ui.bootstrap','config.service'])
        .config(config)
        .controller('ConfigCtrl', configCtrl)
})();