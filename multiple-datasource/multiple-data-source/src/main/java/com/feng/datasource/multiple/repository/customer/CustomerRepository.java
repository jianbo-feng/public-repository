package com.feng.datasource.multiple.repository.customer;

import com.feng.datasource.multiple.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Ding, Shuo
 * @description:
 * @create: 2019-01-22 16:29
 **/
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer > {
}
