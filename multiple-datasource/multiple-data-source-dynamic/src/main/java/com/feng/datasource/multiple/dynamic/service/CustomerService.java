package com.feng.datasource.multiple.dynamic.service;

import com.feng.datasource.multiple.dynamic.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> find(Customer customer);

    List<Customer> getAll();
}
