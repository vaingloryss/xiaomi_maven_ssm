package com.vainglory.service.serviceImpl;

import com.vainglory.mapper.GoodsTypeMapper;
import com.vainglory.pojo.GoodsType;
import com.vainglory.service.IGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/25 0025 下午 1:15
 */
@Service("goodsTypeService")
@Transactional
public class GoodsTypeServiceImpl implements IGoodsTypeService {

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public List<GoodsType> getHeaderGoodsType() {
        return goodsTypeMapper.findByLevel();
    }
}
