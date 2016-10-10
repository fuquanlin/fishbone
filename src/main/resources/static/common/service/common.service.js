/**
 * Created by fuquanlin on 2016/9/28.
 */
angular.module('common.service', ['api.service'])
    .factory('CommonService', ['ApiService', function (ApiService) {

        var _Service = {};

        _Service.doLogin = function (param, successFunc) {
            ApiService.request({
                method: 'POST',
                url: Settings.API + "/login",
                params: param
            }, successFunc);
        };

        _Service.doLogout = function (successFunc) {
            ApiService.request({
                method: 'GET',
                url: Settings.API + "/logout"
            }, successFunc);
        };


        return _Service;

    }]);
