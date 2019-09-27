package com.vainglory.service.serviceImpl;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.vainglory.mapper.*;
import com.vainglory.pojo.Order;
import com.vainglory.pojo.OrderDetail;
import com.vainglory.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/27 0027 下午 3:42
 */
@Service("orderService")
@Transactional
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<Order> showOrder(Integer uid) {
        List<Order> orders = orderMapper.findByUid(uid);
        for (Order order : orders) {
            order.setAddress(addressMapper.findById(order.getAid()));
        }
        return orders;
    }

    @Override
    public Order orderDetail(String oid) {
        Order order = orderMapper.findByOid(oid);
        order.setAddress(addressMapper.findById(order.getAid()));
        List<OrderDetail> orderDetails = orderDetailMapper.findByOid(oid);
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setGoods(goodsMapper.findByGoodsId(orderDetail.getPid()));
        }
        order.setOrderDetails(orderDetails);
        return order;
    }

    @Override
    public void addOrder(Order order) {
        //添加订单
        orderMapper.addOrder(order);
        //添加订单详情
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            orderDetailMapper.addOrderDetail(orderDetail);
        }
        //清空购物车
        cartMapper.deleteByUid(order.getUid());
    }
}
