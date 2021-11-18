/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiroas.service;

import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.tiroas.entity.Product;
import com.tiroas.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author Famisanar
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Product createProduct(Product product) {
        return productRepository.createProduct(product);
    }

    public Product updateProduct(Product product) {

        if (product.getId() != 0) {
            Optional<Product> productoDb = productRepository.getProductById(product.getId());
            if (!productoDb.isEmpty()) {
                if (product.getNombre() != null) {
                    productoDb.get().setNombre(product.getNombre());
                }
                if (product.getCategoria() != null) {
                    productoDb.get().setCategoria(product.getCategoria());
                }
                if (product.getDescripcion() != null) {
                    productoDb.get().setDescripcion(product.getDescripcion());
                }
                if (product.getCantidad() != 0) {
                    productoDb.get().setCantidad(product.getCantidad());
                }
                if (product.getPrecio() != 0.0) {
                    productoDb.get().setPrecio(product.getPrecio());
                }
                productRepository.updateProduct(productoDb.get());
                return productoDb.get();
            } else {
                return product;
            }
        } else {
            return product;
        }
    }

    public List< Product> getAllProduct() {
        return this.productRepository.getAllProduct();
    }

    public Optional<Product> getProductById(int productId) {

        return this.productRepository.getProductById(productId);
    }

    public boolean deleteProduct(int productId) {
        Boolean aBoolean = getProductById(productId).map(product -> {
            productRepository.deleteProduct(product);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    public List<Product> productosxPrecio(double precio) {
        return productRepository.productosxPrecio(precio);
    }

    public List<Product> productosxCategoria(String categoria) {
        return productRepository.productosxCategoria(categoria);
    }

    public List<Product> productosxNombre(String nombre) {
        return productRepository.productosxNombre(nombre);
    }

    public List<String> getAllCategories() {
        List<String> categoryList = new ArrayList<>();
        MongoCollection mongoCollection = mongoTemplate.getCollection("productos");
        DistinctIterable distinctIterable = mongoCollection.distinct("categoria", String.class);
        MongoCursor cursor = distinctIterable.iterator();
        while (cursor.hasNext()) {
            String category = (String) cursor.next();
            categoryList.add(category);
        }
        
        return categoryList;
    }

}
