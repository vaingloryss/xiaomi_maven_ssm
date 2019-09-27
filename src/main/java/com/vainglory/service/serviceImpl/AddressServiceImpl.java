package com.vainglory.service.serviceImpl;

import com.vainglory.mapper.AddressMapper;
import com.vainglory.pojo.Address;
import com.vainglory.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/26 0026 下午 4:56
 */
@Service("addressService")
@Transactional
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public List<Address> showAddress(Integer uid) {
        return addressMapper.findByUid(uid);
    }

    @Override
    public void addAddress(Address address) {
        addressMapper.add(address);
    }

    @Override
    public void deleteAddress(Integer aid) {
        addressMapper.delete(aid);
    }

    @Override
    public void updateAddress(Address address) {
        addressMapper.update(address);
    }

    @Override
    public void defaultAddress(Integer uid, Integer aid) {
        addressMapper.setDefault(uid,aid);
    }

    @Override
    public List<Address> getAddresses(Integer uid) {
        return addressMapper.findByUid(uid);
    }
}
