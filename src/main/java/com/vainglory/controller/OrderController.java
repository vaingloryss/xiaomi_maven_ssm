package com.vainglory.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.vainglory.pojo.*;
import com.vainglory.service.IAddressService;
import com.vainglory.service.ICartService;
import com.vainglory.service.IOrderService;
import com.vainglory.util.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/27 0027 下午 3:36
 */

@Controller
@RequestMapping("/orderController/")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private IAddressService addressService;

    @RequestMapping(value = "showOrder",method = RequestMethod.GET)
    public String showOrder(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "login";
        }
        List<Order> orders = orderService.showOrder(user.getId());
        model.addAttribute("orders",orders);
        return "orderList";
    }

    @RequestMapping(value = "orderDetail",method = RequestMethod.GET)
    public String showOrderDetail(String oid,Model model){
        System.out.println("orderController日志:orderDetail");
        Order order = orderService.orderDetail(oid);
        model.addAttribute("order",order);
        return "orderDetail";
    }

    @RequestMapping(value = "getOrderView",method = RequestMethod.GET)
    public String getOrderView(Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        List<Cart> carts = cartService.findByUid(user.getId());
        model.addAttribute("carts",carts);
        List<Address> addresses = addressService.getAddresses(user.getId());
        model.addAttribute("addresses",addresses);
        return "order";
    }

    //orderController/orderServlet?op=addOrder&aid=11
    @RequestMapping(value = "addOrder",method = RequestMethod.GET)
    public String addOrder(Integer aid,HttpServletRequest request,Model model){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "login";
        }
        List<Cart> carts = cartService.findByUid(user.getId());
        String orderId = RandomUtils.createOrderId();

        List<OrderDetail> orderDetails = new ArrayList<>();
        BigDecimal sum = new BigDecimal(0);
        for (Cart cart : carts) {
            OrderDetail orderDetail = new OrderDetail(null,orderId,cart.getGid(),cart.getNum(),cart.getMoney());
            orderDetails.add(orderDetail);
            sum = sum.add(cart.getMoney());
        }
        //创建订单
        Order order = new Order(orderId,user.getId(),sum,"1",new Date(),aid);
        order.setOrderDetails(orderDetails);
        orderService.addOrder(order);
        model.addAttribute("order",order);
        return "orderSuccess";

    }
}
