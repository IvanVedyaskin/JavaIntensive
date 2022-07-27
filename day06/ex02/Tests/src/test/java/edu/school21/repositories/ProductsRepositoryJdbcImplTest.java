package edu.school21.repositories;


import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductsRepositoryJdbcImplTest {
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new ArrayList<>(Arrays.asList(new Product(0L, "Ivan", 777),
            new Product(1L, "Dimon", 0), new Product(2L, "Vovan", 1337),
            new Product(3L, "Siviy", -100), new Product(4L, "Bmw", 99999999)));
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(0L, "Ivan", 777);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(0L, "user", 12345);
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS2 = new ArrayList<>(Arrays.asList(new Product(0L, "Ivan", 777),
            new Product(1L, "Dimon", 0), new Product(2L, "Vovan", 1337),
            new Product(3L, "Siviy", -100), new Product(4L, "Bmw", 99999999),
            new Product(5L, "user", 24680)));
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS3 = new ArrayList<>(Arrays.asList(new Product(1L, "Dimon", 0), new Product(2L, "Vovan", 1337),
            new Product(3L, "Siviy", -100), new Product(4L, "Bmw", 99999999)));
    EmbeddedDatabase ds;
    ProductsRepositoryJdbcImpl tmp;
    @BeforeEach
    void init(){
        ds = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        tmp = new ProductsRepositoryJdbcImpl(ds);
    }

    @Test
    void checkFindAll(){
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, tmp.findAll());
    }

    @Test
    void checkFindById(){
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, tmp.findById(0L).get());
    }

    @Test
    void checkUpdate(){
        tmp.update(new Product(0L, "user", 12345));
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, tmp.findById(0L).get());
    }

    @Test
    void checkSave(){
        tmp.save(new Product(5L, "user", 24680));
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS2, tmp.findAll());
    }

    @Test
    void checkDelete(){
        tmp.delete(0L);
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS3, tmp.findAll());
    }
}
