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

        $rootScope.multiSelect_language_zh = {
            selectAll: "Select All",
            selectNone: "Cancel All",
            reset: "Reset",
            search: "Search",
            nothingSelected: "Please select"         //default-label is deprecated and replaced with this.
        }
        

    }

    angular.module('main', ['common.service'])
        .config(config)
        .controller('MainCtrl', mainCtrl)
})();