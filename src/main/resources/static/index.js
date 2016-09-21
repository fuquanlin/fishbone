/**
 * Created by fuquanlin on 2016/9/12.
 */
(function () {
    'use strict';

    angular.element(document).ready(function () {
        angular.bootstrap(document, ['app']);
    });

    function config($stateProvider) {
        $stateProvider
            .state('root', {
                url: "",
                views: {
                    'main': {
                        templateUrl: 'templates/main.tpl.html',
                        controller: 'MainCtrl'
                    }
                }
            })
    }

    function RootCtrl($log, $rootScope, $scope, $state) {
        $rootScope.title = "Fishbone UI";

        $rootScope.displayMain = false;

        $log.debug('RootCtrl loaded!');

        $scope.login = function () {
            $log.debug('Main loaded!');
            $rootScope.displayMain = true;
        }

        $rootScope.showLoading = function () {
            $rootScope.isLoading = true;
        }
        $rootScope.hideLoading = function () {
            $rootScope.isLoading = false;
        }
        $rootScope.showToast = function (type, msg, handler, data, ifMoreShowtoast) {
            alert("todo");
        }
    }

    function run($log) {
        $log.info("running");
    }


    angular.module('app', [
            'ui.router',
            'main',
            'welcome',
            'user',
            'user.service',
            'role',
            'role.service'
        ])
        .config(config)
        .controller('RootCtrl', RootCtrl)
        .run(run)
        .value('version', '1.1.0');


})();