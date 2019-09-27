package com.vainglory.mapper;

import com.vainglory.pojo.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/27 0027 下午 7:12
 */
public interface OrderDetailMapper {
    List<OrderDetail> findByOid(@Param("oid") String oid);

    void addOrderDetail(OrderDetail orderDetail);
}
