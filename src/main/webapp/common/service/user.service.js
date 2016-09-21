angular.module('user.service', ['api.service'])
    .factory('UserService', ['ApiService', function (ApiService) {

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