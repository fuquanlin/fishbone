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

    function roleCtrl($log, $scope,RoleService) {
        $log.debug("welcome role ctrl");
        RoleService.queryRole(null, function (response) {
            $scope.roleList = response.model.rows;
        });

    }

    angular.module('role', ['role.service'])
        .config(config)
        .controller('RoleCtrl', roleCtrl)
})();