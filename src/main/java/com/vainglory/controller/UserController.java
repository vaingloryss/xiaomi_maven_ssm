package com.vainglory.controller;

import cn.dsna.util.images.ValidateCode;
import com.vainglory.pojo.User;
import com.vainglory.service.IUserService;
import com.vainglory.service.serviceImpl.UserServiceImpl;
import com.vainglory.util.MD5Util;
import com.vainglory.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequestMapping("/userController/")
@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "toLogin",method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }
    @RequestMapping(value = "toRegister",method = RequestMethod.GET)
    public String toRegister(){
        return "register";
    }
    @RequestMapping(value = "toHome",method = RequestMethod.GET)
    public String toHome(){
        return "home";
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    public String register(User user,@RequestParam("repassword") String repassword,Model model){
        if (StringUtils.isEmpty(user.getUsername())){
            model.addAttribute("registerMSG","用户名不能为空");
            System.out.println("用户名不能为空");
            return "register";
        }
        if (StringUtils.isEmpty(user.getPassword())){
            model.addAttribute("registerMSG","密码不能为空");
            System.out.println("密码不能为空");
            return "register";
        }
        if (!user.getPassword().equals(repassword)){
            model.addAttribute("registerMSG","两次密码不一致");
            System.out.println("两次密码不一致");
            return "register";
        }
        if (StringUtils.isEmpty(user.getEmail())){
            model.addAttribute("registerMSG","邮箱不能为空");
            System.out.println("邮箱不能为空");
            return "register";
        }
        userService.register(user);
        return "registerSuccess";
    }

    @ResponseBody
    @RequestMapping(value = "checkUsername",method = RequestMethod.POST)
    public String checkUsername(String username){
        if(username==null||username.trim().length()==0){
            System.out.println("null");
            return null;
        }
        User user = userService.checkUserName(username);
        if(user!=null){
            return "1";
        }
        return "0";
    }

    @RequestMapping(value = "validateCode",method = RequestMethod.GET)
    public void validateCode(HttpServletRequest request,HttpServletResponse response){
        ValidateCode validateCode = new ValidateCode(100,40,4,10);
        request.getSession().setAttribute("code",validateCode.getCode());
        try {
            validateCode.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "checkValidateCode",method = RequestMethod.POST)
    public String checkValidateCode(String code,HttpServletRequest request){
        String result = "1";
        if (code.equalsIgnoreCase(request.getSession().getAttribute("code").toString())){
            result = "0";
        }
        System.out.println(result);
        return result;
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(String username,String password,String auto,HttpServletRequest request,HttpServletResponse response){
        String loginMSG = "";
        User user = userService.checkUserName(username);
        if (user!=null){
            if (user.getPassword().equals(MD5Util.encode(password))){
                //密码正确，判断是否勾选自动登录
                if (auto!=null){
                    //勾选自动登录
                    Cookie[] cookies = request.getCookies();
                    boolean r = false;
                    for (Cookie cookie : cookies) {
                        if ("autoUser".equals(cookie.getName())){
                            r = true;
                            break;
                        }
                    }
                    if (!r){
                        Cookie cookie = new Cookie("autoUser",user.getUsername()+":"+user.getPassword());
                        cookie.setPath("/");
                        cookie.setMaxAge(14*24*60*60);
                        response.addCookie(cookie);
                    }
                }
            }else {
                loginMSG="密码不正确";
            }
        }else {
            loginMSG = "用户名不正确";
        }
        if (loginMSG.length()==0){
            request.getSession().setAttribute("user",user);
            return "home";
        }else {
            request.getSession().setAttribute("loginMSG",loginMSG);
            return "login";
        }
    }

    @RequestMapping(value = "logOut",method = RequestMethod.GET)
    public String logOut(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.invalidate();
        Cookie cookie = new Cookie("autoUser","");
        cookie.setPath("/");
        response.addCookie(cookie);
        return "home";
    }
}
