package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;

import java.util.List;

/** 收货地址业务层接口 */
public interface IAddressService {

    /**
     * 新增收获地址
     * @param uid
     * @param username
     * @param address
     */
    void addNewAddress(Integer uid, String username, Address address);

    /**
     * 获取收获地址列表
     * @param uid
     * @return
     */
    List<Address> getByUid(Integer uid);

    /**
     * 修改某个用户的收获地址数据为默认收获地址
     * @param aid  收货地址aid
     * @param uid  用户id
     * @param username  修改执行人
     */
    void setDefault(Integer aid, Integer uid, String username);

    /**
     * 删除用户选中的收货地址数据
     * @param aid 收货地址id
     * @param uid 用户id
     * @param username 用户名
     */
    void deleteByAid(Integer aid,Integer uid,String username);


    /**
     * 修改用户地址信息
     * @param aid
     * @param username
     * @param address
     */
    void updateInfoAddress(Integer aid,String username,Address address);


    Address getByAid(Integer aid);


}
