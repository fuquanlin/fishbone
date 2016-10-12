angular.module('role.service', ['api.service'])
    .factory('RoleService', ['ApiService', function (ApiService) {

        var _Service = {};

        _Service.queryRole = function (param, successFunc) {
            ApiService.request({
                method: 'GET',
                url: Settings.API + "/role/list",
                params: param
            }, successFunc);
        };

        return _Service;

    }]);