package com.feng.rocket.rocketbean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 因为序列化和反序列化需要构造函数
 *
 * 这时候实体OrderPaidEvent应该如下：加上一个无参的构造方法 @NoArgsConstructor，所以以后得注意了，在序列化和反序列化时，Java实体类是需要一个无参的构造方法的
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderPaidEvent implements Serializable {

    private String orderId;
    private Integer paidMoney;
}
