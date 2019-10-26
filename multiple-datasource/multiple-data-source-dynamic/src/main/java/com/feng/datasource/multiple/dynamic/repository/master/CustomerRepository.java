package com.feng.datasource.multiple.dynamic.repository.master;

import com.feng.datasource.multiple.dynamic.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository {

    /**
     * 根据条件查询
     * @param customer
     * @return
     */
    List<Customer> find(Customer customer);

    List<Customer> findAll();

}
