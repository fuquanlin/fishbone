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

    function roleCtrl($log, $scope,$rootScope,RoleService) {
        $log.debug("welcome role ctrl");

        $scope.paramQuery = angular.copy(Settings.PAGE);
        var search = function () {
            RoleService.queryRole($scope.paramQuery, function (response) {
                $scope.roleList = response.model.rows;
                $scope.paramQuery.pageIndex = response.model.pageInfo.pageIndex;
                $scope.paramQuery.pageCount = response.model.pageInfo.pageCount;
            });
        }

        search();

        $scope.pageChanged = function () {
            search();
        };

        $scope.delete = function (row) {
            $rootScope.showConfirm("Do you want to delete this role?",function () {
                RoleService.deleteRole(row.id,function () {
                    $rootScope.showAlert("Delete successfully！");
                    search();
                });
            })
        };

    }

    angular.module('role', ['role.service'])
        .config(config)
        .controller('RoleCtrl', roleCtrl)
})();