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

        $scope.getUserData = function () {
            CommonService.getUserData(function (response) {
                var userDataList = response.model;
                $scope.resourceTree = [];
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
                        childPermission =[userDataList[i]];
                        lastPermission = userDataList[i];
                        parentCategory = category;
                    }
                    if (i == userDataList.length - 1 || lastPermission.category != userDataList[i].category) {
                        parentCategory.child = childPermission;
                        if(childPermission.length == 1){
                            parentCategory.final = true;
                        }else{
                            parentCategory.final = false; 
                        }
                    }
                }
                $timeout(function () {
                    $('#side-menu').metisMenu();
                },1000);
            });
        };

        $scope.getUserData();



    }

    angular.module('main', ['common.service'])
        .config(config)
        .controller('MainCtrl', mainCtrl)
})();