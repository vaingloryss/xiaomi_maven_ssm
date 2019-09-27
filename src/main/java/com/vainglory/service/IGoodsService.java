package com.vainglory.service;

import com.vainglory.pojo.Goods;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/26 0026 上午 8:29
 */
public interface IGoodsService {
    List<Goods> miGaming(Integer typeId,Integer pageNum);

    Goods goodsDetail(Integer goodsId);

    Goods findById(Integer goodsId);
}
