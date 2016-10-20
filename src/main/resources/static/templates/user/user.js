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

        search();

        $scope.pageChanged = function () {
            search();
        };

        $scope.add = function () {
            $uibModal.open({
                templateUrl: 'user_management.tpl.html',
                controller: function ($scope, $uibModalInstance) {

                    $scope.ok = function () {
                        $uibModalInstance.close();
                    };

                    $scope.close = function () {
                        $uibModalInstance.close();
                    };
                }
            });
        };

        $scope.edit = function (row) {
            $uibModal.open({
                templateUrl: 'user_management.tpl.html',
                controller: function ($scope, $uibModalInstance) {

                    $scope.ok = function () {
                        $uibModalInstance.close();
                    };

                    $scope.close = function () {
                        $uibModalInstance.close();
                    };
                }
            });
        };

        $scope.delete = function (row) {
            $rootScope.showConfirm("Do you want to delete this user?", function () {
                UserService.deleteUser(row.id, function () {
                    $rootScope.showAlert("Delete successfully！");
                    search();
                });
            })
        };

    }

    angular.module('user', ['ui.bootstrap', 'user.service'])
        .config(config)
        .controller('UserCtrl', userCtrl)
})();