/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiroas.repository.crud;

import com.tiroas.entity.Product;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Famisanar
 */
@Repository
public interface ProductCrudRepository extends MongoRepository<Product, Integer> {
    public List<Product> findByPrecioLessThanEqual(double precio);

    public List<Product> findByCategoria(String categoria);

    @Query("{'nombre':{'$regex':'?0','$options':'i'}}")
    public List<Product> findByNombreLike(String nombre);
}
