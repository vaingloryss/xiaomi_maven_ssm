package com.vainglory.service;

import com.vainglory.pojo.Order;

import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/27 0027 下午 3:42
 */
public interface IOrderService {

    List<Order> showOrder(Integer id);

    Order orderDetail(String oid);

    void addOrder(Order order);
}
