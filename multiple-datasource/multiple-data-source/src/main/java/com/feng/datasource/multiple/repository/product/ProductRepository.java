package com.feng.datasource.multiple.repository.product;

import com.feng.datasource.multiple.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Ding, Shuo
 * @description:
 * @create: 2019-01-22 16:29
 **/
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
