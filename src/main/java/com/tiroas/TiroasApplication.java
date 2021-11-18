package com.tiroas;

import com.mongodb.client.DistinctIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.tiroas.entity.Product;
import com.tiroas.repository.crud.ProductCrudRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class TiroasApplication implements CommandLineRunner {

    @Autowired
    private ProductCrudRepository productCrudRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(TiroasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        productCrudRepository.deleteAll();

        productCrudRepository.saveAll(List.of(
                new Product(1, "RATONES", "MAPOCALIPSIS", "Descripción", 150000, true, 10, ""),
                new Product(2, "RATONES", "MNANOBKB", "Descripción 1", 100000, true, 10, ""),
                new Product(3, "RATONES", "MPANZER", "Descripción 2", 120000, true, 10, ""),
                new Product(4, "RATONES", "MADVBKB", "Descripción 3", 150000, true, 10, ""),
                new Product(5, "Caja Micro ATX", "DEMOLITION", "Descripción 4", 130000, true, 10, ""),
                new Product(6, "Caja ATX", "EXCLUSIVE", "Descripción 5", 135000, true, 10, ""),
                new Product(7, "Caja ATX", "ELEGANT", "Descripción 6", 145000, true, 10, ""),
                new Product(8, "Caja ATX", "EXCELLENT", "Descripción 7", 155000, true, 10, ""),
                new Product(9, "ALTAVOCES", "W380", "Descripción 8", 130000, true, 10, ""),
                new Product(10, "ALTAVOCES", "Y300", "Descripción 9", 120000, true, 10, ""),
                new Product(11, "ALTAVOCES", "Y700", "Descripción 11", 110000, true, 10, ""),
                new Product(12, "Caja ATX", "6625", "Descripción 12", 105000, true, 10, ""),
                new Product(13, "Caja MicroATX", "NAIN", "Descripción 13", 100000, true, 10, ""),
                new Product(14, "Caja MiniITX", "OLIMPO", "Descripción 14", 90000, true, 10, ""),
                new Product(15, "Caja Slim", "LOUNGE3B", "Descripción 15", 80000, true, 10, ""),
                new Product(16, "Fuente Alimentación", "PS-07-600W", "Descripción 16", 50000, true, 10, ""),
                new Product(17, "Fuente Alimentación", "PS901SX", "Descripción 17", 600000, true, 10, ""),
                new Product(18, "Fuente Alimentación", "PS701SX", "Descripción 18", 400000, true, 10, ""),
                new Product(19, "Fuente Alimentación", "PS600SX", "Descripción 19", 300000, true, 10, ""),
                new Product(20, "Fuente Alimentación", "PS500SX", "Descripción 20", 800000, true, 10, "")
        ));

        System.out.println("Productos de menos de : 80000");
        productCrudRepository.findByPrecioLessThanEqual(80000).forEach(System.out::println);

        System.out.println("Productos de la categoria :  ALTAVOCES");
        productCrudRepository.findByCategoria("ALTAVOCES").forEach(System.out::println);

        System.out.println("Productos con el nombre :  Fuente");
        productCrudRepository.findByNombreLike("0SX").forEach(System.out::println);

        List<String> categoryList = new ArrayList<>();
        MongoCollection mongoCollection = mongoTemplate.getCollection("productos");
        DistinctIterable distinctIterable = mongoCollection.distinct("categoria", String.class);
        MongoCursor cursor = distinctIterable.iterator();
        while (cursor.hasNext()) {
            String category = (String) cursor.next();
            categoryList.add(category);
        }

        System.out.println("Listado de categorias");
        categoryList.forEach(System.out::println);

    }
}
