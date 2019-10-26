package com.feng.datasource.multiple.dynamic.service;

import com.feng.datasource.multiple.dynamic.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> find(Product product);

    List<Product> getAll();
}
