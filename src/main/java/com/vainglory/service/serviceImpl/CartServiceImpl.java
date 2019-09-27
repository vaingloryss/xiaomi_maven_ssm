package com.vainglory.service.serviceImpl;

import com.vainglory.mapper.CartMapper;
import com.vainglory.mapper.GoodsMapper;
import com.vainglory.pojo.Cart;
import com.vainglory.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/26 0026 上午 11:24
 */

@Service("cartService")
@Transactional
public class CartServiceImpl implements ICartService {

    @Autowired
    CartMapper cartMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<Cart> showCart(Integer uid) {
        List<Cart> carts = cartMapper.findByUid(uid);
        if (carts!=null){
            for (Cart cart : carts) {
                cart.setGoods(goodsMapper.findByGoodsId(cart.getGid()));
            }
        }
        return carts;
    }

    @Override
    public Cart findByUidAndGid(Integer uid, Integer goodsId) {
        return cartMapper.findByUidAndGid(uid,goodsId);
    }

    @Override
    public void addCart(Cart cart) {
        cartMapper.addCart(cart);
    }

    @Override
    public void updateCart(Cart cart) {
        cartMapper.updateCart(cart);
    }

    @Override
    public void deleteCart(Integer uid, Integer goodsId) {
        cartMapper.deleteByUidAndGid(uid,goodsId);
    }

    @Override
    public void clearCart(Integer uid) {
        cartMapper.deleteByUid(uid);
    }

    @Override
    public List<Cart> findByUid(Integer uid) {
        List<Cart> carts = cartMapper.findByUid(uid);
        for (Cart cart : carts) {
            cart.setGoods(goodsMapper.findByGoodsId(cart.getGid()));
        }
        return carts;
    }
}
