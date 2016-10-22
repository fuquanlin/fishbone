/**
 * Created by fuquanlin on 2016/9/12.
 */
(function () {
    'use strict';

    angular.element(document).ready(function () {
        angular.bootstrap(document, ['app']);
    });

    function config($stateProvider,$urlRouterProvider) {
        $stateProvider
            .state('root', {
                url: "",
                views: {
                    'main': {
                        templateUrl: 'templates/main.tpl.html',
                        controller: 'MainCtrl'
                    }
                }
            });
        $urlRouterProvider.otherwise('/welcome');
    }

    function RootCtrl($log, $rootScope, $scope, $timeout, $uibModal, $state,cfpLoadingBar, CommonService) {
        $scope.user = {};
        $rootScope.title = "Fishbone UI";

        $rootScope.displayMain = false;


        $log.debug('RootCtrl loaded!');

        $scope.login = function () {
            $log.debug('Main loaded!');
            CommonService.doLogin($scope.user, function () {
                $scope.getUserData();
                $state.reload();
            });
        };

        $scope.quickLogin = function (evt) {
            if (evt.keyCode == 13) {
                $scope.login();
            }
        };

        $rootScope.showLoading = function () {
            cfpLoadingBar.start();
        };

        $rootScope.hideLoading = function () {
            cfpLoadingBar.complete();
        };

        $rootScope.showAlert = function (msg, handler) {
            $rootScope.showToast("Alert",msg, handler)
        };

        $rootScope.showConfirm = function (msg, handler) {
            $rootScope.showToast("Confirm",msg, handler)
        };

        $rootScope.showToast = function (title, msg, handler) {

            $uibModal.open({
                templateUrl: 'common_dialog.tpl.html',
                controller: function ($scope, $uibModalInstance) {

                    $scope.title = title;
                    $scope.content = msg;

                    $scope.ok = function () {
                        if (handler) {
                            handler();
                        }
                        $uibModalInstance.close();
                    };

                    $scope.close = function () {
                        $uibModalInstance.close();
                    };
                }
            });

        };


        $scope.getUserData = function () {
            CommonService.getUserData(function (response) {
                var userDataList = response.model;
                $rootScope.resourceTree = [];
                var lastPermission = null;
                var parentCategory = null;
                var childPermission = null;
                for (var i = 0; i < userDataList.length; i++) {
                    if (lastPermission != null && lastPermission.category == userDataList[i].category) {
                        if (childPermission == null) {
                            childPermission = [userDataList[i]];
                        } else {
                            childPermission.push(userDataList[i]);
                        }
                    } else {
                        var category = angular.copy(userDataList[i]);
                        $scope.resourceTree.push(category);
                        childPermission = [userDataList[i]];
                        lastPermission = userDataList[i];
                        parentCategory = category;
                    }
                    if (i == userDataList.length - 1 || userDataList[i].category == null || lastPermission.category != userDataList[i].category) {
                        parentCategory.child = childPermission;
                        if (childPermission.length == 1) {
                            parentCategory.final = true;
                        } else {
                            parentCategory.final = false;
                        }
                    }
                }
                debugger;


                $timeout(function () {
                    $rootScope.displayMain = true;
                    $('#side-menu').metisMenu();
                }, 1000);
            });
        };

        $scope.getUserData();


    }

    function run($log) {
        $log.info("running");
    }

    var moduleList = [
        'chieffancypants.loadingBar',
        'ui.bootstrap',
        'ui.router',
        'main',
        'common',
        'common.service',
        'welcome',
        'user',
        'user.service',
        'role',
        'role.service',
        'log',
        'log.service'
    ];
    if (window.inBuild) {
        moduleList.push('templates');
    }

    angular.module('app', moduleList)
        .config(config)
        .controller('RootCtrl', RootCtrl)
        .run(run)
        .value('version', '1.0.0');


})();