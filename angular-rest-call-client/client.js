/**
 * Created by movses on 2/23/16.
 */
var app = angular.module('container', []);
app.controller('actionController', function($scope, $http) {

    $scope.method = "get";

    $scope.hideBox =  function() {
        $scope.show = false;
        $scope.responseShow = false;
    };

    $scope.showBox =  function() {
        $scope.show = true;
    };

    $scope.clear = function() {
        $scope.url = null;
        $scope.requestBody= null;
        $scope.responseBody = null;
        $scope.responseShow = false;
    }

    $scope.send = function() {
        $scope.responseShow = true;

        $http({
            method : $scope.method,
            url : $scope.url,
            data : $scope.requestBody,
            headers : {
                'Content-Type': 'application/json'
            }
        }).then(function success(response) {
            $scope.responseBody = JSON.stringify(response.data);
        }, function error(response) {
            $scope.responseBody = response.statusText;
        });
    }

});

app.config(['$httpProvider', function($httpProvider) {
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common["X-Requested-With"];
}
]);