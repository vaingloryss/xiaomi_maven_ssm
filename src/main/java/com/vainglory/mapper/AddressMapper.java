package com.vainglory.mapper;

import com.vainglory.pojo.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author vaingloryss
 * @date 2019/9/26 0026 下午 4:59
 */
public interface AddressMapper {
    List<Address> findByUid(@Param("uid") Integer uid);

    void add(Address address);

    void delete(@Param("aid") Integer aid);

    void update(Address address);

    void setDefault(@Param("uid") Integer uid,@Param("aid") Integer aid);

    Address findById(@Param("aid") Integer aid);
}
