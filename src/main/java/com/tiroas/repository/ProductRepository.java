/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiroas.repository;

import com.tiroas.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.tiroas.repository.crud.ProductCrudRepository;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Famisanar
 */
@Repository
public class ProductRepository {

    @Autowired
    private ProductCrudRepository productCrudRepository;

    public Product createProduct(Product Productt) {
        return productCrudRepository.save(Productt);
    }

    public void updateProduct(Product product) {
        productCrudRepository.save(product);
    }

    public List< Product> getAllProduct() {
        return productCrudRepository.findAll();
    }

    public Optional<Product> getProductById(int productId) {
        return productCrudRepository.findById(productId);
    }

    public void deleteProduct(Product product) {

        productCrudRepository.delete(product);
    }  
    
    public List<Product> productosxPrecio(double precio){
        return productCrudRepository.findByPrecioLessThanEqual(precio);
    }
    
    public List<Product> productosxCategoria(String categoria){
        return productCrudRepository.findByCategoria(categoria);
    }
    
    public List<Product> productosxNombre(String nombre){
        return productCrudRepository.findByNombreLike(nombre);
    }
    
}
