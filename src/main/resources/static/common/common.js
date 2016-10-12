(function () {
    'use strict';

    function formatDate() {
        return function (text) {
            if (text) {
                var date = new Date(text);
                var year = date.getFullYear();
                var month = (date.getMonth() + 1);
                var day = date.getDate();
                var hour = date.getHours();
                var minute = date.getMinutes();
                var second = date.getSeconds();
                month = month < 10 ? ("0" + month) : month;
                day = day < 10 ? ("0" + day) : day;
                hour = hour < 10 ? ("0" + hour) : hour;
                minute = minute < 10 ? ("0" + minute) : minute;
                second = second < 10 ? ("0" + second) : second;
                return (year + "/" + month + "/" + day + " " + hour + ":" + minute + ":" + second);
            }
        }
    }

    angular.module('common', [])
        .filter('formatDate', formatDate);

})();