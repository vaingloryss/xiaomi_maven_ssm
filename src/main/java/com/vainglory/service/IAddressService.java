package com.vainglory.service;

import com.vainglory.pojo.Address;

import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/26 0026 下午 4:52
 */
public interface IAddressService {
    List<Address> showAddress(Integer uid);

    void addAddress(Address address);

    void deleteAddress(Integer aid);

    void updateAddress(Address address);

    void defaultAddress(Integer id, Integer aid);

    List<Address> getAddresses(Integer uid);
}
