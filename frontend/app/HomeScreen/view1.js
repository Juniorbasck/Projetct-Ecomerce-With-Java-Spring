'use strict';

angular.module('myApp.view1', ['ngRoute', 'ngMaterial'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view1', {
    templateUrl: 'HomeScreen/view1.html',
    controller: 'View1Ctrl'
  });
}])

.controller('View1Ctrl', ['$scope', '$http', function($scope, $http) {

  $http.get('http://44.223.70.8:8080/products')
    .then(function(response) {
      $scope.produtos = response.data;
    })
    .catch(function(error) {
      console.error('Erro ao obter a lista de produtos', error);
    });



    $scope.adicionarNovoProduto = function() {

      var precoNumero = parseFloat($scope.novoProduto.price);
  
      var novoProduto = {
          name: $scope.novoProduto.name,
          price: precoNumero
      };
  
      $http.post('http://44.223.70.8:8080/products', novoProduto)
          .then(function(response) {
              console.log('Novo produto adicionado com sucesso:', response.data);
  
              $scope.novoProduto = {};
              $scope.mensagemSucesso = 'Novo produto adicionado com sucesso!';
  
              return $http.get('http://44.223.70.8:8080/products');
          })
          .then(function(response) {
              $scope.produtos = response.data;
          })
          .catch(function(error) {
              console.error('Erro ao adicionar novo produto', error);
          });
    };

     $scope.adicionarAoCarrinho = function(produtoId) {
      var cartId = 2;
  
      var itemRequest = {
          productId: produtoId,
          amount: 1
      };
  
      console.log(cartId, itemRequest.productId, itemRequest.amount);
  
      $http.post('http://44.223.70.8:8080/items/' + cartId, itemRequest)
          .then(function(response) {
              console.log('Item adicionado ao carrinho com sucesso:', response.data);

              // $scope.mensagemDoServidor = 'produto adcionado do carrinho';
          })
          .catch(function(error) {
              console.error('Erro ao adicionar item ao carrinho', error);
          });
      };
}]);