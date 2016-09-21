(function () {
    'use strict';

    function config($stateProvider) {
        $stateProvider
            .state('user', {
                templateUrl: 'templates/user/user.tpl.html',
                controller: 'UserCtrl'
            })
    }

    function userCtrl($log, $scope) {
        $log.debug("welcome user ctrl");

    }

    angular.module('user', [])
        .config(config)
        .controller('UserCtrl', [
            '$log', '$scope', userCtrl])
})();