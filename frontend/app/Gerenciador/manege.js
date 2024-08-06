'use strict';

angular.module('myApp.manege', ['ngRoute', 'ngMaterial'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/manege', {
    templateUrl: 'Gerenciador/manege.html',
    controller: 'Manegetrl'
  });
}])

angular.module('myApp.login')
    .controller('Manegetrl', ['$scope', '$http', '$routeParams', '$mdDialog', '$window',  function($scope, $http, $routeParams, $mdDialog, $window) {
      
      $scope.user = JSON.parse($window.localStorage.getItem('user'));

      $scope.isAdmin = function() {
        return $scope.user && $scope.user.isAdmin;
      }

      //------------

      $http.get('http://44.223.70.8:8080/products')
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

        if (!$scope.isAdmin()) {
          $scope.showNoPermissionDialog(event);
          return;
        }

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
      
            console.log('Produto editado:', editedProduct);
      
            $http.put('http://44.223.70.8:8080/products/' + editedProduct.id, editedProduct)
              .then(function (response) {
                console.log('Produto editado com sucesso:', response.data);
      
                $http.get('http://44.223.70.8:8080/products')
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
      
      $scope.excluirProduto = function(id, event) {

        if (!$scope.isAdmin()) {
          $scope.showNoPermissionDialog(event);
          return;
        }

        console.log("to aqui")
        $http.delete('http://44.223.70.8:8080/products/' + id)
            .then(function(response) {
                console.log('Produto excluído com sucesso:', response.data);
    
                $scope.produtos = $scope.produtos.filter(function(produto) {
                    return produto.id !== id;
                });
  
                $scope.mensagemDoServidor = 'Produto excluído com sucesso';
                
            })
            .catch(function(error) {
              console.error('Erro ao excluir produto', error);
              $scope.showAlert(event);
            });
       };

       $scope.showAlert = function (ev) {
        $mdDialog.show(
            $mdDialog.alert()
                .parent(angular.element(document.body))
                .clickOutsideToClose(true)
                .title('Erro ao excluir')
                .textContent('Não foi possivel exlcuir o produto')
                .ariaLabel('Erro!')
                .ok('Entendi')
                .targetEvent(ev)
        );
    };

    $scope.showNoPermissionDialog = function (ev) {
      $mdDialog.show(
          $mdDialog.alert()
              .parent(angular.element(document.body))
              .clickOutsideToClose(true)
              .title('Sem permissão')
              .textContent('Você não tem permissão para realizar esta ação.')
              .ariaLabel('Erro!')
              .ok('Entendi')
              .targetEvent(ev)
      );
    };
}]);