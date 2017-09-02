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



        var refresh = function () {
            $scope.paramQuery.pageIndex = 1;
            search();
        };

        search();

        $scope.pageChanged = function () {
            search();
        };

        var initMultiSelectData = function (inputData, selectedData) {
            var outputData = [];
            for (var i = 0; i < inputData.length; i++) {
                var d = inputData[i];
                var selected = false;
                if (selectedData && selectedData.length) {
                    for (var j = 0; j < selectedData.length; j++) {
                        if (d.id == selectedData[j].id) {
                            selected = true;
                        }
                    }
                }
                outputData.push(angular.extend({}, d, {tick: selected}));
            }

            return outputData;
        };

        var resetSelectData = function (inputData) {
            var outputData = [];
            if (inputData && inputData.length) {
                for (var i = 0; i < inputData.length; i++) {
                    outputData.push(angular.extend({}, inputData[i], {tick: false}));
                }
            }
            return outputData;
        };

        $scope.add = function () {
            $uibModal.open({
                size: 'lg',
                templateUrl: 'role_management.tpl.html',
                controller: function ($scope, $uibModalInstance,RoleService,PermissionService) {

                    $scope.noneSelect = false;

                    $scope.fItemClick = function () {
                        var selectPermissionArray = $scope.model.permissions;
                        var length = selectPermissionArray.length;
                        if (length > 0) {
                            $scope.noneSelect = false;
                        } else {
                            $scope.noneSelect = true;
                        }

                    };

                    $scope.fReset = function () {
                        $scope.model.permissions = [];
                        $scope.noneSelect = true;
                        $scope.permissionList = resetSelectData($scope.permissionList);
                    };

                    PermissionService.queryPermission({"pageIndex": 1, "pageCount": 1000}, function (response) {
                        $scope.permissionList = response.model.rows;
                        $scope.permissionList = initMultiSelectData($scope.permissionList, []);
                    });

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
                    controller: function ($scope, $uibModalInstance,RoleService,PermissionService) {
                        $scope.model = model;
                        debugger;

                        $scope.noneSelect = false;

                        $scope.fItemClick = function () {
                            var selectPermissionArray = $scope.model.permissions;
                            var length = selectPermissionArray.length;
                            if (length > 0) {
                                $scope.noneSelect = false;
                            } else {
                                $scope.noneSelect = true;
                            }

                        };

                        $scope.fReset = function () {
                            $scope.model.permissions = [];
                            $scope.noneSelect = true;
                            $scope.permissionList = resetSelectData($scope.permissionList);
                        };

                        PermissionService.queryPermission({"pageIndex": 1, "pageCount": 1000}, function (response) {
                            $scope.permissionList = response.model.rows;
                            $scope.permissionList = initMultiSelectData($scope.permissionList, $scope.model.permissions);
                        });

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

    angular.module('role', ['ui.bootstrap', 'role.service','permission.service'])
        .config(config)
        .controller('RoleCtrl', roleCtrl)
})();