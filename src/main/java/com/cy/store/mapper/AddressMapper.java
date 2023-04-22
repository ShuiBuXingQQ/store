package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/** 收货地址持久层接口 */
public interface AddressMapper {

    /**
     * 插入用户的收获地址数据
     * @param address 收货地址顺序
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户的id 统计收货地址总数
     * @param uid 用户id
     * @return 当前用户的收货地址总数
     */
    Integer countByUid(Integer uid);

    /**
     * 通过用户uid，查找收货地址列表信息
     * @param uid
     * @return  收货地址数据
     */
    List<Address> findByUid(Integer uid);

    /**
     * 根据aid 查询收获地址数据
     * @param aid
     * @return 收货地址数据，没有则返回null
     */
    Address findByAid(Integer aid);

    /**
     *根据uid 来修改用户的收货地址为非默认
     * @param uid
     * @return
     */
    Integer updateNoDefault(Integer uid);

    /**
     * 根据用户的aid 来修改为 默认地址
     * @param aid
     * @return
     */
    Integer  updateDefaultByAid(@Param("aid") Integer aid,
                                @Param("modifiedTime") Date modifiedTime,
                                @Param("modifiedUser")String modifiedUser);

    /**
     * 根据收货地址id 删除收货地址数据
     * @param aid 收货地址id
     * @return 受影响的行数
     */
    Integer deleteByAid(Integer aid);

    /**
     * 根据用户uid 查询当前用户最后一次被修改的地址数据
     * @param uid 用户id
     * @return 收货地址数据
     */
    Address findLastModified(Integer uid);


    /**
     * 根据用户 aid 来修改用户信息地址信息
     * @param address
     * @return
     */
    Integer  updateInfoByAid(Address address);

}
