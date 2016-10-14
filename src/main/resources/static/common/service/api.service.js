angular.module('api.service', ['ngFileUpload']).config(['$provide', function ($provide) {
    $provide.factory('ApiService', ['$http', '$rootScope', 'Upload', function ($http, $rootScope, $upload) {
        return {
            request: function (config, successFunc, errFunc) {
                config = angular.extend({
                    url: Settings.API,
                    cache: false,
                    withCredentials: true
                }, config);

                // get 请求暂时不显示loading
                if (config.method && config.method.toUpperCase() != "GET") {
                    $rootScope.showLoading();
                }

                $http(config).success(function (result) {
                    $rootScope.hideLoading();
                    var success = result["success"], errorCode = result['errorCode'], errorMsg = result["errorMsg"];
                    /* 处理异常 */
                    if (!success) {

                        var msg = {
                            errorCode: errorCode,
                            errorMsg: errorMsg
                        };

                        $rootScope.errorMsg = errorMsg;

                        if (msg.errorCode != 3) {//not authentication error
                            $rootScope.showToast("error", msg);
                        }

                        console.log("throwableMsg :" + result["throwableMsg"]);
                        return;
                    }

                    if (successFunc) {
                        successFunc(result);
                    } else {
                        return result;
                    }

                }).error(function (result) {
                    if (errFunc) {
                        errFunc(result);
                    } else {
                        $rootScope.hideLoading();
                        $rootScope.showToast("error", "网络或服务器异常,请稍后再试.");
                    }
                });
            },
            requestUpload: function (config, progressFunc, successFunc) {
                config = angular.extend({
                    url: Settings.API,
                    cache: false,
                    withCredentials: true
                }, config);

                $upload.upload(config).progress(function (evt) {
                    if (progressFunc)progressFunc(evt);
                }).success(function (result) {
                    var success = result["success"], errorCode = result['errorCode'], errorMsg = result["errorMsg"];
                    /* 处理异常 */
                    if (!success) {

                        var msg = {
                            errorCode: errorCode,
                            errorMsg: errorMsg
                        };

                        $rootScope.showToast("error", msg);
                        console.log("throwableMsg :" + result["throwableMsg"]);
                        return;
                    }

                    if (successFunc) {
                        successFunc(result);
                    } else {
                        return result;
                    }
                }).error(function (result) {
                    $rootScope.showToast("error", "网络或服务器异常,请稍后再试.");
                    console.log("upload failed->" + result);
                });
            },
            open: function (url) {
                window.open(url);
            }

        };
    }]);
}]);