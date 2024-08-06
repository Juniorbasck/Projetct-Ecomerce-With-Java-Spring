'use strict';

angular.module('myApp.view2', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view2', {
    templateUrl: 'CartiScreen/view2.html',
    controller: 'View2Ctrl'
  });
}])

angular.module('myApp.view2')
    .controller('View2Ctrl', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {
    
    var cartId = 2;

    function getCartItems() {
        $http.get('http://localhost:8080/cart/' + cartId)
            .then(function(response) {
                $scope.carrinhoItens = response.data.cart.item;
                $scope.carrinhoTotal = response.data;
                console.log('$scope.carrinhoItens:', $scope.carrinhoItens);
            })
            .catch(function(error) {
                console.error('Erro ao obter itens do carrinho', error);
            });
    }

    getCartItems(); 

      /*---------------*/

      $scope.aumentarQuantidade = function(item) {
          item.amount += 1;
      };

      $scope.diminuirQuantidade = function(item) {
        if (item.amount > 1) {
            item.amount -= 1;
        }
      };

      $scope.atualizarCarrinho = function() {
        var cartId = 2;
    
        var itemsToUpdate = [];
    
        $scope.carrinhoItens.forEach(function(item) {
            if (item.amount !== item.originalAmount) {
                itemsToUpdate.push({
                    productId: item.product.id,
                    amount: item.amount
                });
            }
        });
        
        $http.put('http://localhost:8080/cart/' + cartId, itemsToUpdate)
        .then(function(response) {
            console.log('Carrinho atualizado com sucesso:', response.data);
        })
        .catch(function(error) {
            console.error('Erro ao atualizar carrinho', error);
        });
    };
    

    
    $scope.removerDoCarrinho = function(itemId) {
      var cartId = 2

      $http.delete('http://localhost:8080/cart/' + cartId + '/' + itemId)
          .then(function(response) {
              console.log('Produto removido do carrinho com sucesso:', response.data);
              
              getCartItems();
          })
          .catch(function(error) {
              console.error('Erro ao remover produto do carrinho', error);

              $scope.mensagemDoServidor = 'Erro ao remover produto do carrinho';
          });
      };
}]);