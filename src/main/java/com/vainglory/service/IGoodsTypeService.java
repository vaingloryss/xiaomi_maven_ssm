package com.vainglory.service;

import com.vainglory.pojo.GoodsType;

import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/25 0025 下午 1:09
 */
public interface IGoodsTypeService {
    List<GoodsType> getHeaderGoodsType();
    GoodsType findById(Integer id);

    List<GoodsType> findAll();

}
