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

    function userCtrl($log, $scope,UserService) {
        $log.debug("welcome user ctrl");
        UserService.queryUser(null, function (response) {
            $scope.userList = response.model.rows;
        });

     
    }

    angular.module('user', ['user.service'])
        .config(config)
        .controller('UserCtrl', userCtrl)
})();