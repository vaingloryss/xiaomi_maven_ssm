package com.vainglory.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vainglory.mapper.GoodsMapper;
import com.vainglory.mapper.GoodsTypeMapper;
import com.vainglory.pojo.Goods;
import com.vainglory.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.text.normalizer.CharTrie;

import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/26 0026 上午 8:30
 */

@Service("goodsService")
@Transactional
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsTypeMapper goodsTypeMapper;

    private static final Integer PAGESIZE=8;

    @Override
    public List<Goods> miGaming(Integer typeId,Integer pageNum) {
        List<Goods> goods = goodsMapper.findByGoodsTypeId(typeId);
        return goods;
    }

    @Override
    public Goods goodsDetail(Integer goodsId) {
        return goodsMapper.findByGoodsId(goodsId);
    }

    @Override
    public Goods findById(Integer goodsId) {
        return goodsMapper.findByGoodsId(goodsId);
    }

    @Override
    public List<Goods> findAll() {
        List<Goods> goodsList = goodsMapper.findAll();
        for (Goods goods : goodsList) {
            goods.setGoodsType(goodsTypeMapper.findById(goods.getTypeid()));
        }
        return goodsList;
    }

    @Override
    public void addGoods(Goods goods) {
        goodsMapper.add(goods);
    }
}
