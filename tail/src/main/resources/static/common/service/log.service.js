angular.module('log.service', ['api.service'])
    .factory('LogService', ['ApiService', function (ApiService) {

        var _Service = {};

        _Service.queryLog = function (param, successFunc) {
            ApiService.request({
                method: 'GET',
                url: Settings.API + "/log/list",
                params: param
            }, successFunc);
        };

        return _Service;

    }]);