package com.feng.datasource.multiple.dynamic.service.impl;


import com.feng.datasource.multiple.dynamic.entity.Product;
import com.feng.datasource.multiple.dynamic.repository.cluster.ProductRepository;
import com.feng.datasource.multiple.dynamic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> find(Product product) {
        return productRepository.find(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
