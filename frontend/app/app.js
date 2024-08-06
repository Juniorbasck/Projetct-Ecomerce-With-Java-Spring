'use strict';

angular.module('myApp', [
  'ngRoute',
  'myApp.cabecalho',
  'myApp.view1',
  'myApp.view2',
  'myApp.login',
  'myApp.manege',
  'myApp.version'
]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');

  $routeProvider.otherwise({redirectTo: '/login'});
}]);
