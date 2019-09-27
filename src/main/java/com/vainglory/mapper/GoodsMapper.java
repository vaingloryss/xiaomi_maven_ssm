package com.vainglory.mapper;

import com.vainglory.pojo.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/26 0026 上午 8:33
 */

public interface GoodsMapper {
    List<Goods> findByGoodsTypeId(Integer typeId);
    Goods findByGoodsId(@Param("goodsId") Integer goodsId);
}
