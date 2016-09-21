/**
 * Created by fuquanlin on 2016/9/12.
 */
(function () {
    'use strict';

    function config() {
    }

    function mainCtrl($log, $scope,$state) {
        $log.debug("welcome main ctrl");
        $('#side-menu').metisMenu();

        $scope.chooseMenu = function (moduleName) {
            $state.go(moduleName);
        }

    }

    angular.module('main', [])
        .config(config)
        .controller('MainCtrl', [
            '$log', '$scope','$state', mainCtrl])
})();