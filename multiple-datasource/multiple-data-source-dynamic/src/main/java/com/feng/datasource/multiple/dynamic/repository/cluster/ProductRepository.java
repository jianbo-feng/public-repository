package com.feng.datasource.multiple.dynamic.repository.cluster;

import com.feng.datasource.multiple.dynamic.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {

    List<Product> find(Product product);

    List<Product> findAll();
}
