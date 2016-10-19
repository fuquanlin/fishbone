(function () {
    'use strict';

    function config($stateProvider) {
        $stateProvider
            .state('root.user', {
                url:"/user",
                templateUrl: 'templates/user/user.tpl.html',
                controller: 'UserCtrl'
            })
    }

    function userCtrl($log, $scope,$rootScope,UserService) {
        $log.debug("welcome user ctrl");

        $scope.paramQuery ={'pageIndex':'1','pageCount':'5'};

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

        $scope.edit = function (row) {
           $rootScope.showToast("Alert","test test?")
        };

        $scope.delete = function (row) {

        };
     
    }

    angular.module('user', ['user.service'])
        .config(config)
        .controller('UserCtrl', userCtrl)
})();