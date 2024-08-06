package com.desafio.backend.product.Controller;

import com.desafio.backend.cart.Service.CartRepository;
import com.desafio.backend.product.Product;
import com.desafio.backend.product.Service.ProductReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductReposity productService;

    @Autowired
    private CartRepository cartRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
            productService.deleteById(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public Product UpdateProduct(@PathVariable Long id,@RequestBody Product product){

        Product existingProduct = productService.getProductById(id);

        if(existingProduct != null){
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setPromotion(product.getPromotion());

            return productService.save(existingProduct);
        }
        return null;
    }
}
