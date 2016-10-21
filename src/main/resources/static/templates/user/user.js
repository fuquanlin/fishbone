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

        $scope.add = function () {
            $uibModal.open({
                size: 'lg',
                templateUrl: 'user_management.tpl.html',
                controller: function ($scope, $uibModalInstance,UserService) {

                    $scope.ok = function () {
                        UserService.createUser($scope.model,function () {
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
            UserService.getUser(row.id,function (response) {
                var model = response.model;
                $uibModal.open({
                    size: 'lg',
                    templateUrl: 'user_management.tpl.html',
                    controller: function ($scope, $uibModalInstance,UserService) {
                        $scope.model = model;
                        $scope.ok = function () {
                            UserService.updateUser($scope.model,function () {
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

    angular.module('user', ['ui.bootstrap', 'user.service'])
        .config(config)
        .controller('UserCtrl', userCtrl)
})();