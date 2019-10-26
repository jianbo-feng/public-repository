package com.feng.datasource.multiple.service.impl;

import com.feng.datasource.multiple.entity.customer.Customer;
import com.feng.datasource.multiple.entity.product.Product;
import com.feng.datasource.multiple.repository.customer.CustomerRepository;
import com.feng.datasource.multiple.repository.product.ProductRepository;
import com.feng.datasource.multiple.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyServiceImpl implements MyService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Customer> allCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public List<Product> allProduct() {
        return productRepository.findAll();
    }
}
