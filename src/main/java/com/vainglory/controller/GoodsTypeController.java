package com.vainglory.controller;

import com.alibaba.fastjson.JSON;
import com.vainglory.pojo.GoodsType;
import com.vainglory.service.IGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/25 0025 下午 1:07
 */
@Controller
@RequestMapping("/goodsTypeController/")
public class GoodsTypeController {

    @Autowired
    private IGoodsTypeService goodsTypeService;

    @ResponseBody
    //@RequestMapping(value = "headerGoodsType",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @RequestMapping(value = "headerGoodsType",method = RequestMethod.GET)
    public String headerGoodsType(){
        List<GoodsType> goodsTypes = goodsTypeService.getHeaderGoodsType();
        String goodsTypesStr = JSON.toJSONString(goodsTypes);
        return goodsTypesStr;
    }
}
