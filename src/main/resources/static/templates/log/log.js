(function () {
    'use strict';

    function config($stateProvider) {
        $stateProvider
            .state('root.log', {
                url:"/log",
                templateUrl: 'templates/log/log.tpl.html',
                controller: 'LogCtrl'
            })
    }

    function logCtrl($log, $scope,$uibModal,LogService) {
        $log.debug("welcome log ctrl");
        LogService.queryLog(null, function (response) {
            $scope.logList = response.model.rows;
        });

        $scope.showDetail = function (opsLog) {
            $uibModal.open({
                size: 'lg',
                templateUrl: 'opsLog_detail.tpl.html',
                controller: function ($scope, LogService, $uibModalInstance) {
                    $scope.opsLog = opsLog;
                    $scope.oldValue = JSON.parse(opsLog.oldValue);
                    $scope.newValue = JSON.parse(opsLog.newValue);
                    $scope.mixValue = angular.extend({}, $scope.newValue, $scope.oldValue);

                    $scope.close = function () {
                        $uibModalInstance.close();
                    };
                }
            });
        };

    }

    angular.module('log', ['ui.bootstrap','log.service'])
        .config(config)
        .controller('LogCtrl', logCtrl)
})();