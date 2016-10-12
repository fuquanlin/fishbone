angular.module('user.service', ['api.service'])
    .factory('UserService', ['ApiService', function (ApiService) {

        var _Service = {};

        _Service.queryUser = function (param, successFunc) {
            ApiService.request({
                method: 'GET',
                url: Settings.API + "/user/list",
                params: param
            }, successFunc);
        };


        return _Service;

    }]);