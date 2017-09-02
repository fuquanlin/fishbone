angular.module('permission.service', ['api.service'])
    .factory('PermissionService', ['ApiService', function (ApiService) {

        var _Service = {};

        _Service.queryPermission = function (param, successFunc) {
            ApiService.request({
                method: 'GET',
                url: Settings.API + "/permission/list",
                params: param
            }, successFunc);
        };


        return _Service;

    }]);