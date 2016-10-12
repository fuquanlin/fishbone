/**
 * Created by fuquanlin on 2016/9/12.
 */
(function () {
    'use strict';

    function config() {
    }

    function mainCtrl($log, $scope, $state, $rootScope, $timeout,CommonService) {
        $log.debug("welcome main ctrl");


        $scope.chooseMenu = function (moduleName) {
            $state.go(moduleName);
        };

        $scope.doLogout = function () {
            $log.debug("Logout");
            CommonService.doLogout(function () {
                $rootScope.displayMain = false;
            });
        };





    }

    angular.module('main', ['common.service'])
        .config(config)
        .controller('MainCtrl', mainCtrl)
})();