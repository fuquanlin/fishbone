(function () {
    'use strict';

    function config($stateProvider) {
        $stateProvider
            .state('root.user', {
                url: "/user",
                templateUrl: 'templates/user/user.tpl.html',
                controller: 'UserCtrl'
            })
    }

    function userCtrl($log, $scope, $rootScope, $uibModal, UserService) {
        $log.debug("welcome user ctrl");

        $scope.paramQuery = angular.copy(Settings.PAGE);

        var search = function () {
            UserService.queryUser($scope.paramQuery, function (response) {
                $scope.userList = response.model.rows;
                $scope.paramQuery.pageIndex = response.model.pageInfo.pageIndex;
                $scope.paramQuery.pageCount = response.model.pageInfo.pageCount;
            });
        };

        var refresh = function () {
            $scope.paramQuery.pageIndex = 1;
            search();
        }

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
        }

        $scope.add = function () {
            $uibModal.open({
                size: 'lg',
                templateUrl: 'user_management.tpl.html',
                controller: function ($scope, $uibModalInstance, UserService, RoleService) {
                    $scope.noneSelect = false;

                    $scope.fItemClick = function () {
                        var selectRoleArray = $scope.model.roles;
                        var length = selectRoleArray.length;
                        if (length > 0) {
                            $scope.noneSelect = false;
                        } else {
                            $scope.noneSelect = true;
                        }

                    };

                    $scope.fReset = function () {
                        $scope.model.roles = [];
                        $scope.noneSelect = true;
                        $scope.roleList = resetSelectData($scope.roleList);
                    };

                    RoleService.queryRole({"pageIndex": 1, "pageCount": 1000}, function (response) {
                        $scope.roleList = response.model.rows;
                        $scope.roleList = initMultiSelectData($scope.roleList, []);
                    });


                    $scope.ok = function () {
                        UserService.createUser($scope.model, function () {
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
            UserService.getUser(row.id, function (response) {
                var model = response.model;
                $uibModal.open({
                    size: 'lg',
                    templateUrl: 'user_management.tpl.html',
                    controller: function ($scope, $uibModalInstance, UserService,RoleService) {
                        $scope.model = model;

                        $scope.noneSelect = false;

                        $scope.fItemClick = function () {
                            var selectRoleArray = $scope.model.roles;
                            var length = selectRoleArray.length;
                            if (length > 0) {
                                $scope.noneSelect = false;
                            } else {
                                $scope.noneSelect = true;
                            }

                        };

                        $scope.fReset = function () {
                            $scope.model.roles = [];
                            $scope.noneSelect = true;
                            $scope.roleList = resetSelectData($scope.roleList);
                        };

                        RoleService.queryRole({"pageIndex": 1, "pageCount": 1000}, function (response) {
                            $scope.roleList = response.model.rows;
                            $scope.roleList = initMultiSelectData($scope.roleList,  $scope.model.roles);
                        });

                        $scope.ok = function () {
                            UserService.updateUser($scope.model, function () {
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
            $rootScope.showConfirm("Do you want to delete this user?", function () {
                UserService.deleteUser(row.id, function () {
                    $rootScope.showAlert("Delete successfully！");
                    refresh();
                });
            })
        };

    }

    angular.module('user', ['ui.bootstrap','isteven-multi-select', 'user.service', 'role.service'])
        .config(config)
        .controller('UserCtrl', userCtrl)
})();