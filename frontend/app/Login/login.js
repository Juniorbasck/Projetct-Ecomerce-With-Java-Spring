'use strict';

angular.module('myApp.login', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'Login/login.html',
    controller: 'LoginCtrl'
  });
}])

angular.module('myApp.login')
    .controller('LoginCtrl', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {
    
  
}]);