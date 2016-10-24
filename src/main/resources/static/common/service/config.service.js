angular.module('config.service', ['api.service'])
    .factory('ConfigService', ['ApiService', function (ApiService) {

        var _Service = {};

        _Service.queryConfig = function (param, successFunc) {
            ApiService.request({
                method: 'GET',
                url: Settings.API + "/config/list",
                params: param
            }, successFunc);
        };

        return _Service;

    }]);