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
    
      $scope.showPrompt = function (ev) {
        // Appending dialog to document.body to cover sidenav in docs app
        var confirm = $mdDialog.prompt()
          .title('What would you name your dog?')
          .textContent('Bowser is a common name.')
          .placeholder('Dog name')
          .ariaLabel('Dog name')
          .initialValue('Buddy')
          .targetEvent(ev)
          .required(true)
          .ok('Okay!')
          .cancel('I\'m a cat person');
    
        $mdDialog.show(confirm).then(function (result) {
          $scope.status = 'You decided to name your dog ' + result + '.';
        }, function () {
          $scope.status = 'You didn\'t name your dog.';
        });
      };
}]);