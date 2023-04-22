package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.DistrictMapper;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/** 新增收货地址实现类 */
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    //在添加用户的收货地址的业务层依赖于 IDistrictMapper的业务层接口
    @Autowired
    private DistrictMapper districtMapper;


    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //调用收获地址统计数量方法
        Integer count = addressMapper.countByUid(uid);
        if (count >= maxCount) {
            throw new AddressCountLimitException("用户收获地址超出上限");
        }

        //对address 对象中的数据进行补全：省市区
        String provinceName = districtMapper.findCodeByName(address.getProvinceCode());
        String cityName = districtMapper.findCodeByName(address.getCityCode());
        String areaName = districtMapper.findCodeByName(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        //uid isDefault
        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0; // 1表示默认，0表示不是默认
        address.setIsDefault(isDefault);
        //补全4个日志字段
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        //插入收获地址的方法
        Integer row = addressMapper.insert(address);
        if ( row != 1) {
            throw new InsertException("插入用户的收获地址时产生未知异常");
        }


    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for (Address address : list) {
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收获地址不存在");
        }
        //检查当前收获地址归属
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法数据访问");
        }
        //将所有收获地址设置为非默认
        Integer rows = addressMapper.updateNoDefault(uid);
        if (rows < 1) {
            throw new UpdateException("更新数据产生未知异常");
        }
        //将用户的某条地址设置为收获地址
         rows = addressMapper.updateDefaultByAid(aid, new Date(), username);
        if (rows != 1) {
            throw new UpdateException("更新数据产生未知异常");
        }


    }

    @Override
    public void deleteByAid(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法数据访问");
        }
        Integer rows = addressMapper.deleteByAid(aid);
        if (rows != 1) {
            throw new DeleteException("删除数据时产生未知异常");
        }
        Integer count = addressMapper.countByUid(uid);
        if (count == 0) {
            return; //该用户统计地址数据为0，直接终止程序
        }

        if (result.getIsDefault() == 0) {
            return;
        }

        //将最新修改的一条数据 字段is_default 值修改为1
        Address address = addressMapper.findLastModified(uid);
         rows = addressMapper.updateDefaultByAid(address.getAid(), new Date(), username);
        if (rows != 1) {
            throw new UpdateException("更新数据时产生未知异常");
        }


    }

    @Override
    public void updateInfoAddress(Integer aid, String username, Address address) {
        Address result = addressMapper.findByAid(aid);

        //对address 对象中的数据进行补全：省市区
        String provinceName = districtMapper.findCodeByName(address.getProvinceCode());
        String cityName = districtMapper.findCodeByName(address.getCityCode());
        String areaName = districtMapper.findCodeByName(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        address.setIsDefault(result.getIsDefault());
        address.setAid(aid);
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        //插入收获地址的方法
        Integer row = addressMapper.updateInfoByAid(address);
        if ( row != 1) {
            throw new UpdateException("修改用户的收获地址时产生未知异常");
        }

    }

    @Override
    public Address getByAid(Integer aid) {

        Address result = addressMapper.findByAid(aid);
        if (result == null ) {
            throw new UserNotFondException("用户地址数据不存在");
        }

//        Address address = new Address();
//        address.setUid(result.getUid());

        return result;
    }


}
