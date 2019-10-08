package com.vainglory.controller;

import com.vainglory.pojo.Address;
import com.vainglory.pojo.User;
import com.vainglory.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/26 0026 下午 4:40
 */
@Controller
@RequestMapping("addressController")
public class AddressController {

    @Autowired
    IAddressService addressService;


    @RequestMapping(value = "showAddress",method = {RequestMethod.GET,RequestMethod.POST})
    public String showAddress(HttpServletRequest request, Model model){
        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            return "login";
        }
        List<Address> addresses = addressService.showAddress(user.getId());
        model.addAttribute("addresses",addresses);
        return "self_info";
    }

    @RequestMapping(value = "addAddress",method = RequestMethod.POST)
    public String addAddress(Address address){
        //0为可选地址
        address.setLevel(0);
        addressService.addAddress(address);
        return "forward:showAddress";
    }

    @RequestMapping(value = "deleteAddress",method = RequestMethod.GET)
    public String deleteAddress(Integer aid){
        addressService.deleteAddress(aid);
        return "forward:showAddress";
    }

    @RequestMapping(value = "updateAddress",method = RequestMethod.POST)
    public String updateAddress(Address address){
        addressService.updateAddress(address);
        return "forward:showAddress";
    }

    @RequestMapping(value = "defaultAddress")
    public String defaultAddress(Integer aid,HttpServletRequest request){
        System.out.println("Address日志: defaultAddress");
        User user = (User) request.getSession().getAttribute("user");
        addressService.defaultAddress(user.getId(),aid);
        return "forward:showAddress";
    }
}
