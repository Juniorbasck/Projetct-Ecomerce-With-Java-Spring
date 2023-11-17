'use strict';

angular.module('myApp.view1', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view1', {
    templateUrl: 'view1/view1.html',
    controller: 'View1Ctrl'
  });
}])

.controller('View1Ctrl', ['$scope', '$http', function($scope, $http) {

  $http.get('http://localhost:8080/products')
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
  
      $http.post('http://localhost:8080/products', novoProduto)
          .then(function(response) {
              console.log('Novo produto adicionado com sucesso:', response.data);
  
              $scope.novoProduto = {};
              $scope.mensagemSucesso = 'Novo produto adicionado com sucesso!';
  
              return $http.get('http://localhost:8080/products');
          })
          .then(function(response) {
              $scope.produtos = response.data;
          })
          .catch(function(error) {
              console.error('Erro ao adicionar novo produto', error);
          });
    };

    $scope.excluirProduto = function(id) {
      $http.delete('http://localhost:8080/products/' + id)
          .then(function(response) {
              console.log('Produto excluído com sucesso:', response.data);
  
              $scope.produtos = $scope.produtos.filter(function(produto) {
                  return produto.id !== id;
              });
          })
          .catch(function(error) {
              console.error('Erro ao excluir produto', error);
          });
     };
}]);