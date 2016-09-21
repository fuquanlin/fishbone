angular.module('role.service', ['api.service'])
    .factory('RoleService', ['ApiService', function (ApiService) {

        var _Service = {};

        _Service.getEmployees = function (param, successFunc) {
            ApiService.request({
                method: 'GET',
                url: Settings.API + "/employees",
                params: param
            }, successFunc);
        };


        return _Service;

    }]);