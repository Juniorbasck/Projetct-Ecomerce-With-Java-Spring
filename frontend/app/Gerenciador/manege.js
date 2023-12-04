'use strict';

angular.module('myApp.manege', ['ngRoute', 'ngMaterial'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/manege', {
    templateUrl: 'Gerenciador/manege.html',
    controller: 'Manegetrl'
  });
}])

angular.module('myApp.login')
    .controller('Manegetrl', ['$scope', '$http', '$routeParams', '$mdDialog', function($scope, $http, $routeParams, $mdDialog) {
      
      $http.get('http://localhost:8080/products')
      .then(function(response) {
        $scope.produtos = response.data;
      })
      .catch(function(error) {
        console.error('Erro ao obter a lista de produtos', error);
      });

      //---------------------

      $scope.status = ' ';
      $scope.customFullscreen = false;

      $scope.showEditDialog = function (ev, produto) {
        var originalProduct = angular.copy(produto);
        var editedProduct = angular.copy(produto);
      
        var confirm = $mdDialog.prompt()
          .title('Editar Produto')
          .textContent('Edite o nome do produto:')
          .ariaLabel('Editar Nome')
          .placeholder('Nome do produto')
          .initialValue(originalProduct.name)
          .required(true)
          .ok('Próximo')
          .cancel('Cancelar');
      
        $mdDialog.show(confirm).then(function (nameResult) {
          editedProduct.name = nameResult;
      
          // Agora, adicione outro prompt para editar o preço
          var pricePrompt = $mdDialog.prompt()
            .title('Editar Produto')
            .textContent('Edite o preço do produto:')
            .ariaLabel('Editar Preço')
            .placeholder('Preço do produto')
            .initialValue(originalProduct.price)
            .required(true)
            .ok('Salvar')
            .cancel('Cancelar');
      
          $mdDialog.show(pricePrompt).then(function (priceResult) {
            editedProduct.price = priceResult;
      
            // Aqui você pode fazer algo com o produto editado, como enviá-lo para o servidor
            console.log('Produto editado:', editedProduct);
      
            // Enviar os dados editados para o backend
            $http.put('http://localhost:8080/products/' + editedProduct.id, editedProduct)
              .then(function (response) {
                console.log('Produto editado com sucesso:', response.data);
      
                // Atualizar a lista de produtos
                $http.get('http://localhost:8080/products')
                  .then(function (response) {
                    $scope.produtos = response.data;
                  })
                  .catch(function (error) {
                    console.error('Erro ao obter a lista de produtos', error);
                  });
      
                $scope.status = 'Produto editado com sucesso: ' + editedProduct.name + ' - R$ ' + editedProduct.price;
              })
              .catch(function (error) {
                console.error('Erro ao editar produto', error);
      
                $scope.status = 'Erro ao editar produto.';
              });
      
          }, function () {
            $scope.status = 'Edição do preço cancelada.';
          });
      
        }, function () {
          $scope.status = 'Edição do produto cancelada.';
        });
      };

      //-----------------------------------


      //---------------------------------
      
      $scope.excluirProduto = function(id) {
        console.log("to aqui")
        $http.delete('http://localhost:8080/products/' + id)
            .then(function(response) {
                console.log('Produto excluído com sucesso:', response.data);
    
                $scope.produtos = $scope.produtos.filter(function(produto) {
                    return produto.id !== id;
                });
  
                $scope.mensagemDoServidor = 'Produto excluído com sucesso';
                
            })
            .catch(function(error) {
              console.error('Erro ao excluir produto', error);
  
              $scope.mensagemDoServidor = 'Erro ao excluir produto.';
  
            });
       };
}]);