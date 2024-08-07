'use strict';

angular.module('myApp.login', ['ngRoute', 'ngMaterial'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'Login/login.html',
    controller: 'LoginCtrl'
  });
}])

angular.module('myApp.login')
      .controller('LoginCtrl', ['$scope', '$http', '$location', '$mdDialog', '$window', function($scope, $http, $location, $mdDialog, $window,) {    

        $scope.submitHandler = function(event) {
          event.preventDefault();

          var email = $scope.email;
          var password = $scope.password;

          var data = {
              email: email,
              password: password
          };

          $http.post('http://junior-trabalho.myftp.org:8080/users/login', data)
              .then(function(response) {
                  // Sucesso no login
                  var user = response.data;
                  console.log('Login bem-sucedido:', user);
                  
                  $window.localStorage.setItem('user', JSON.stringify(user));

                  $location.path('/view1');  
              })
              .catch(function(error) {
                  console.error('Erro no login:', error.data);

                  $scope.showAlert(event);
              });
      };

      
}]);