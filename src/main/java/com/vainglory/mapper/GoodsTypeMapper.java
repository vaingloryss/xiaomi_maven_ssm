package com.vainglory.mapper;

import com.vainglory.pojo.GoodsType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/25 0025 下午 1:16
 */
public interface GoodsTypeMapper {
    List<GoodsType> findByLevel();
    GoodsType findById(@Param("id")Integer id);

    List<GoodsType> findAll();

}
