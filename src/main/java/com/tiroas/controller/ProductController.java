/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiroas.controller;

import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.tiroas.entity.Product;
import com.tiroas.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Famisanar
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/productos")
public class ProductController {
   @Autowired
    private ProductService productService;
   
    @GetMapping("/all")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") int id) {
        return productService.getProductById(id).get();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {

        return productService.createProduct(product);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Product update(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id) {
        return productService.deleteProduct(id);
    }
    
    @GetMapping("/categoria/{categoria}")
    public List<Product> productosxCategoria(@PathVariable("categoria") String categoria){
        return productService.productosxCategoria(categoria);
    }
    
    @GetMapping("/precio/{precio}")
    public List<Product> productosxPrecio(@PathVariable("precio") double precio){
        return productService.productosxPrecio(precio);
    }
    
    @GetMapping("/nombre/{nombre}")
    public List<Product> productosxNombre(@PathVariable("nombre") String nombre){
        return productService.productosxNombre(nombre);
    }
    
    
    @GetMapping("/categorias")
    public List<String> categorias(){
        return productService.getAllCategories();
    }
}
