package com.vainglory.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.vainglory.pojo.Cart;
import com.vainglory.pojo.Goods;
import com.vainglory.pojo.User;
import com.vainglory.service.ICartService;
import com.vainglory.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/26 0026 上午 11:17
 */
@Controller
@RequestMapping("/cartController/")
public class CartController {
    @Autowired
    private ICartService cartService;

    @Autowired
    private IGoodsService goodsService;

    @RequestMapping(value = "showCart",method = RequestMethod.GET)
    public String showCart(Model model, HttpServletRequest request){
        //判断有没有登录
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "login";
        }
        List<Cart> carts = cartService.showCart(user.getId());
        model.addAttribute("carts",carts);
        return "cart";
    }

    @RequestMapping(value = "addCart",method = RequestMethod.GET)
    public String addCart(Integer goodsId,Integer number,HttpServletRequest request){
        //判断有没有登录
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "login";
        }
        if (goodsId==null){
            return "home";
        }
        Cart oldCart = cartService.findByUidAndGid(user.getId(),goodsId);
        Goods goods = goodsService.findById(goodsId);
        if (oldCart==null){
            //添加新记录
            Cart newCart = new Cart(user.getId(),goodsId,number,goods.getPrice().multiply(new BigDecimal(number)));
            cartService.addCart(newCart);
            return "cartSuccess";
        }else {
            //修改数量
            if(number==0){
                //删除
                cartService.deleteCart(user.getId(),goodsId);
                return "cartSuccess";
            }
            oldCart.setNum(number+oldCart.getNum());
            oldCart.setMoney(goods.getPrice().multiply(new BigDecimal(oldCart.getNum())));
            cartService.updateCart(oldCart);
            return "cartSuccess";
        }
    }

    @RequestMapping(value = "clearCart",method = RequestMethod.GET)
    public String clearCart(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        cartService.clearCart(user.getId());
        return "cartSuccess";
    }
}
