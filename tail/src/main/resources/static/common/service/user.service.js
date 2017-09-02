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

        _Service.getUser = function (userId, successFunc) {
            ApiService.request({
                method: 'GET',
                url: Settings.API + "/user/" + userId
            }, successFunc);
        };

        _Service.createUser = function (param, successFunc) {
            ApiService.request({
                method: 'POST',
                url: Settings.API + "/user",
                data: param
            }, successFunc);
        };

        _Service.updateUser = function (param, successFunc) {
            ApiService.request({
                method: 'PUT',
                url: Settings.API + "/user",
                data: param
            }, successFunc);
        };
        

        _Service.deleteUser = function (userId, successFunc) {
            ApiService.request({
                method: 'DELETE',
                url: Settings.API + "/user/" + userId
            }, successFunc);
        };


        return _Service;

    }]);