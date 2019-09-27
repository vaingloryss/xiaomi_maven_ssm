package com.vainglory.mapper;

import com.vainglory.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/26 0026 上午 11:26
 */
public interface CartMapper {
    List<Cart> findByUid(@Param("uid") Integer uid);

    Cart findByUidAndGid(@Param("uid") Integer uid, @Param("goodsId") Integer goodsId);

    void updateCart(Cart cart);

    void addCart(Cart cart);

    void deleteByUidAndGid(@Param("uid") Integer uid, @Param("goodsId") Integer goodsId);

    void deleteByUid(Integer uid);
}
