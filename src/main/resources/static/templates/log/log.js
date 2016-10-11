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

    function logCtrl($log, $scope) {
        $log.debug("welcome log ctrl");

    }

    angular.module('log', [])
        .config(config)
        .controller('LogCtrl', logCtrl)
})();