(function () {
    'use strict';

    function config($stateProvider) {
        $stateProvider
            .state('root.welcome', {
                url:"",
                templateUrl: 'templates/welcome/welcome.tpl.html',
                controller: 'WelcomeCtrl'
            })
    }

    function welcomeCtrl($log, $scope) {
        $log.debug("welcome ctrl");

    }

    angular.module('welcome', [])
        .config(config)
        .controller('WelcomeCtrl',  welcomeCtrl)
})();