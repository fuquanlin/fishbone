(function () {
    'use strict';

    function config($stateProvider) {
        $stateProvider
            .state('root.role', {
                url:"/role",
                templateUrl: 'templates/role/role.tpl.html',
                controller: 'RoleCtrl'
            })
    }

    function roleCtrl($log, $scope) {
        $log.debug("welcome role ctrl");

    }

    angular.module('role', [])
        .config(config)
        .controller('RoleCtrl', roleCtrl)
})();