package com.feng.datasource.multiple.service;

import com.feng.datasource.multiple.entity.customer.Customer;
import com.feng.datasource.multiple.entity.product.Product;

import java.util.List;

public interface MyService {

    List<Customer> allCustomer();

    List<Product> allProduct();
}
