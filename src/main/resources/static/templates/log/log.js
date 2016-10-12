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

    function logCtrl($log, $scope,LogService) {
        $log.debug("welcome log ctrl");
        LogService.queryLog(null, function (response) {
            $scope.logList = response.model.rows;
            debugger;
        });

    }

    angular.module('log', ['log.service'])
        .config(config)
        .controller('LogCtrl', logCtrl)
})();