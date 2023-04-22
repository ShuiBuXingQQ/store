package com.cy.store.service;

import com.cy.store.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/** 用户模块业务层接口 */
public interface IUserService {
    /**
     * 用户注册方法
     * @param user  用户数据对象
     */
    void reg(User user);

    /**
     * 用户登录功能
     * @param username 用户名
     * @param password 用户密码
     * @return 当前匹配的用户数据，如果没有则返回null
     */
    User login(String username,String password);

    /**
     * 修改密码功能
     * @param uid
     * @param username
     * @param oldPassword
     * @param newPassword
     */
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);

    /**
     * 根据用户的id查询数据
     * @param uid 用户的id
     * @return 用户的数据
     */
    User getByUid(Integer uid);

    /**
     * 更新用户数据操作
     * @param uid 用户id
     * @param username 用户名称
     * @param user 用户对象数据
     */
    void changeInfo(Integer uid,String username,User user);

    /**
     * 修改用户头像
     * @param uid 用户的id
     * @param avatar 用户头像的路径
     * @param username 用户的名称
     */
    void changeAvatar(Integer uid,
                      String avatar,
                      String username);



    //查询所有
    List<User> queryUserInfo();
    //查询部分数据
    List<User> findAllUserByPageF(int pageNum,int pageSize);
    //查询详细数据
    PageInfo<User> findAllUserByPageS(int pageNum, int pageSize);


}
