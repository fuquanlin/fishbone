(function () {
    'use strict';

    function config($stateProvider) {
        $stateProvider
            .state('root.role', {
                url: "/role",
                templateUrl: 'templates/role/role.tpl.html',
                controller: 'RoleCtrl'
            })
    }

    function roleCtrl($log, $scope, $rootScope, $uibModal, RoleService) {
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

        var refresh = function () {
            $scope.paramQuery.pageIndex = 1;
            search();
        }

        $scope.pageChanged = function () {
            search();
        };

        $scope.add = function () {
            $uibModal.open({
                size: 'lg',
                templateUrl: 'role_management.tpl.html',
                controller: function ($scope, $uibModalInstance,RoleService) {

                    $scope.ok = function () {
                        RoleService.createRole($scope.model,function () {
                            refresh();
                        });
                        $uibModalInstance.close();
                    };

                    $scope.close = function () {
                        $uibModalInstance.close();
                    };
                }
            });
        };

        $scope.edit = function (row) {
            RoleService.getRole(row.id,function (response) {
                var model = response.model;
                $uibModal.open({
                    size: 'lg',
                    templateUrl: 'role_management.tpl.html',
                    controller: function ($scope, $uibModalInstance,RoleService) {
                        $scope.model = model;
                        $scope.ok = function () {
                            RoleService.updateRole($scope.model,function () {
                                refresh();
                            });
                            $uibModalInstance.close();
                        };

                        $scope.close = function () {
                            $uibModalInstance.close();
                        };
                    }
                });
            });

        };

        $scope.delete = function (row) {
            $rootScope.showConfirm("Do you want to delete this role?", function () {
                RoleService.deleteRole(row.id, function () {
                    $rootScope.showAlert("Delete successfully！");
                    refresh();
                });
            })
        };

    }

    angular.module('role', ['ui.bootstrap', 'role.service'])
        .config(config)
        .controller('RoleCtrl', roleCtrl)
})();