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

        _Service.getRole = function (roleId, successFunc) {
            ApiService.request({
                method: 'GET',
                url: Settings.API + "/role/" + roleId
            }, successFunc);
        };

        _Service.createRole = function (param, successFunc) {
            ApiService.request({
                method: 'POST',
                url: Settings.API + "/role",
                data: param
            }, successFunc);
        };

        _Service.updateRole = function (param, successFunc) {
            ApiService.request({
                method: 'PUT',
                url: Settings.API + "/role",
                data: param
            }, successFunc);
        };

        _Service.deleteRole = function (userId, successFunc) {
            ApiService.request({
                method: 'DELETE',
                url: Settings.API + "/role/"+userId
            }, successFunc);
        };

        return _Service;

    }]);