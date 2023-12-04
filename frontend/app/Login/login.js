'use strict';

angular.module('myApp.login', ['ngRoute', 'ngMaterial'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'Login/login.html',
    controller: 'LoginCtrl'
  });
}])

angular.module('myApp.login')
      .controller('LoginCtrl', ['$scope', '$http', '$location', '$mdDialog',  function($scope, $http, $location, $mdDialog) {    

        $scope.submitHandler = function(event) {
          event.preventDefault();

          var email = $scope.email;
          var password = $scope.password;

          var data = {
              email: email,
              password: password
          };

          $http.post('http://localhost:8080/users/login', data)
              .then(function(response) {
                  // Sucesso no login
                  var user = response.data;
                  console.log('Login bem-sucedido:', user);

                  $location.path('/view1');  
              })
              .catch(function(error) {
                  console.error('Erro no login:', error.data);

                  $scope.showAlert(event);
              });
      };

      $scope.showAlert = function (ev) {
        $mdDialog.show(
            $mdDialog.alert()
                .parent(angular.element(document.body))
                .clickOutsideToClose(true)
                .title('Credenciais Inválidas')
                .textContent('A senha fornecida está incorreta. Por favor, tente novamente.')
                .ariaLabel('Alerta de Credenciais Inválidas')
                .ok('Entendi')
                .targetEvent(ev)
        );
    };
}]);