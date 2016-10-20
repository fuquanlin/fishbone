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

        _Service.deleteUser = function (userId, successFunc) {
            ApiService.request({
                method: 'DELETE',
                url: Settings.API + "/user/"+userId
            }, successFunc);
        };


        return _Service;

    }]);