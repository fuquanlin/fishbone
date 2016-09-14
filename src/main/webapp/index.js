/**
 * Created by fuquanlin on 2016/9/12.
 */
(function () {
    'use strict';

    angular.element(document).ready(function () {
        angular.bootstrap(document, ['app']);
    });

    function config(){

    };

    function RootCtrl($log,$rootScope) {
        debugger;
        $rootScope.title = "Fishbone UI";

        $rootScope.displayMain = true;

        $log.debug('RootCtrl loaded!');
    }

    function run($log){
        debugger;
        $log.info("running");
    }



    angular.module('app', [])
        .config(config)
        .controller('RootCtrl', RootCtrl)
        .run(run)
        .value('version', '1.1.0');




})();