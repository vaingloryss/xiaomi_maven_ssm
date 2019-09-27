package com.vainglory.mapper;

import com.vainglory.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/27 0027 下午 3:45
 */
public interface OrderMapper {
    List<Order> findByUid(@Param("uid") Integer uid);
    Order findByOid(@Param("oid") String oid);

    void addOrder(Order order);

}
