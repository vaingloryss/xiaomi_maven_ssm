package com.vainglory.service;

import com.vainglory.pojo.Cart;

import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/26 0026 上午 11:23
 */
public interface ICartService {

    List<Cart> showCart(Integer uid);

    Cart findByUidAndGid(Integer id, Integer goodsId);

    void addCart(Cart newCart);

    void updateCart(Cart oldCart);

    void deleteCart(Integer id, Integer goodsId);

    void clearCart(Integer id);

    List<Cart> findByUid(Integer uid);
}
