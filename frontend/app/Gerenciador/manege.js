'use strict';

angular.module('myApp.manege', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/manege', {
    templateUrl: 'Gerenciador/manege.html',
    controller: 'Manegetrl'
  });
}])

angular.module('myApp.login')
    .controller('Manegetrl', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {
  
}]);