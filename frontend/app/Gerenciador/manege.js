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
    
      $scope.status = ' ';
      $scope.customFullscreen = false;

      $scope.showPrompt = function (ev) {
        var originalProduct = {
          name: 'Buddy', // Valor inicial para o nome do produto
          price: 0 // Valor inicial para o preço do produto
        };

        var editedProduct = angular.copy(originalProduct); // Copia para garantir que a edição não afete o original

        var confirm = $mdDialog.prompt()
          .title('Área de edição')
          .textContent('Edite os campos do produto:')
          .ariaLabel('Edit Product')
          .placeholder('Nome do produto')
          .initialValue(originalProduct.name)
          .required(true)
          .ok('Próximo')
          .cancel('Cancelar');

        $mdDialog.show(confirm).then(function (nameResult) {
          editedProduct.name = nameResult;

          // Agora, adicione outro prompt para editar o preço
          var pricePrompt = $mdDialog.prompt()
            .title('Área de edição')
            .textContent('Edite o preço do produto:')
            .ariaLabel('Edit Product Price')
            .placeholder('Preço do produto')
            .initialValue(originalProduct.price)
            .required(true)
            .ok('Salvar')
            .cancel('Cancelar');

          $mdDialog.show(pricePrompt).then(function (priceResult) {
            editedProduct.price = priceResult;

            // Aqui, você pode fazer algo com o produto editado (por exemplo, enviar para o servidor)
            console.log('Produto editado:', editedProduct);

            $scope.status = 'Produto editado com sucesso: ' + editedProduct.name + ' - R$ ' + editedProduct.price;
          }, function () {
            $scope.status = 'Edição do preço cancelada.';
          });

        }, function () {
          $scope.status = 'Edição do produto cancelada.';
        });
      };


      //-----------------------------------

      $http.get('http://localhost:8080/products')
      .then(function(response) {
        $scope.produtos = response.data;
      })
      .catch(function(error) {
        console.error('Erro ao obter a lista de produtos', error);
      });

      //---------------------------------
      
      $scope.excluirProduto = function(id) {
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