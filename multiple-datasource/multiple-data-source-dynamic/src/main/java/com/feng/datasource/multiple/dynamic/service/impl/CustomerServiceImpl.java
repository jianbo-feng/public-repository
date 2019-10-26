package com.feng.datasource.multiple.dynamic.service.impl;

import com.feng.datasource.multiple.dynamic.entity.Customer;
import com.feng.datasource.multiple.dynamic.repository.master.CustomerRepository;
import com.feng.datasource.multiple.dynamic.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> find(Customer customer) {
        return customerRepository.find(customer);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }
}
