package com.vainglory.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.vainglory.pojo.Goods;
import com.vainglory.pojo.GoodsType;
import com.vainglory.pojo.User;
import com.vainglory.service.IGoodsService;
import com.vainglory.service.IGoodsTypeService;
import com.vainglory.service.IUserService;
import com.vainglory.util.MD5Util;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/10/8 0008 上午 8:01
 */
@Controller
@RequestMapping("/adminController/")
public class AdminController {

    @Autowired
    IUserService userService;
    @Autowired
    IGoodsService goodsService;

    @Autowired
    IGoodsTypeService goodsTypeService;

    //@GetMapping(value = "toLogin")//REST风格，仅支持get请求，推荐使用，语义明确
    @RequestMapping(value = "toLogin",method = RequestMethod.GET)
    public String adminLogin(){
        return "admin/login";
    }

    @GetMapping("toAddGoods")
    public String toAddGoods(){return "admin/addGoods";}

    @GetMapping("toAddGoodsType")
    public String toAddGoodsType(){return "admin/addGoodsType";}

    @GetMapping("toInvalidUser")
    public String toInvalidUser(){return "admin/invalidUser";}

    @GetMapping("toUserList")
    public String toUserList(){return "admin/userList";}

    @RequestMapping(value = "adminLogin",method = RequestMethod.POST)
    public String login(String username, String password, Model model){
        String loginMSG = "";
        User user = userService.checkUserName(username);
        if (user!=null){
            if (user.getPassword().equals(MD5Util.encode(password))){
                model.addAttribute("admin",user);
                return "admin/admin";
            }else {
                loginMSG = "密码错误";
            }
        }else {
            loginMSG="用户不存在";
        }
        model.addAttribute("loginMSG",loginMSG);
        return "admin/userList";
    }

    @PostMapping("addGoods")//REST风格，仅处理POST请求，推荐使用
    public String addGoods( Goods goods, MultipartFile source, HttpSession session) throws IOException {
        System.out.println("adminController日志：addGoods");
        //文件的原始名称
        String originalFilename = source.getOriginalFilename();
        goods.setPicture(originalFilename);
        System.out.println(goods);
        //获取文件的后缀名
        String extension = FilenameUtils.getExtension(originalFilename);
        System.out.println(extension);
        //文件的类型
        String contentType = source.getContentType();
        //获取文件夹的磁盘路径
        String realPath = session.getServletContext().getRealPath("/goodsImg");
        System.out.println(realPath);//输出目录：G:\idea_work\xiaomi_maven_ssm\target\xiaomi_maven_ssm\goodsImg
        //保存商品信息
        goodsService.addGoods(goods);
        //保存文件
        source.transferTo(new File(realPath+File.separator+originalFilename));
        return "forward:getGoodsList";
    }

    @RequestMapping(value = "getGoodsList")
    public String getGoodsList(Model model){
        List<Goods> goodsList = goodsService.findAll();
        model.addAttribute("goodsList",goodsList);
         return "admin/showGoods";
    }

    @GetMapping("getGoodsTypeList")
    public String getGoodsTypeList(Model model){
        List<GoodsType> goodsTypeList = goodsTypeService.findAll();
        model.addAttribute("goodsTypeList",goodsTypeList);
        return "admin/showGoodsType";
    }

    @ResponseBody
    @RequestMapping(value = "getUserList")
    public List<User> getUserList(Integer flag,Model model){
        List<User> userList = userService.getUserList(flag);
        model.addAttribute("userList",userList);
        return userList;
    }

}
