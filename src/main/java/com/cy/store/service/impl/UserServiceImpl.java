package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/** 用户模块业务层的实现类 */ //@Service注解将当前类的对象交给spring来管理，自动创建对象以及对象的维护
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        //通过user参数来获取传递过来的username
        String username = user.getUsername();
        //调用findByUsername（name）判断用户是否注册过
        User result = userMapper.findByUsername(username);
        //判断结果集是否不为null 则抛出用户名被占用异常
        if (result != null) {
            //抛出异常
            throw new UsernameDuplicateException("用户名被占用");
        }


        //密码的加密处理实现，MD5算法的形式
        //串+pwd+串-----MD5算法加密，连续加载三次
        //盐值+pwd+盐值-----盐值就是一个随机的字符串
        String oldPassword = user.getPassword();
        //获取一个 随机生成的盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        //补全数据，将盐值记录到表里
        user.setSalt(salt);
        //将密码和盐值作为一个整体进行加密处理，忽略原有密码强度提升了数据安全性
        String md5Password = getMD5Password(oldPassword,salt);
        //将加密之后的密码重新补全设置到user对象中
        user.setPassword(md5Password);
        //补全数据 is_delete设置成0
        user.setIsDelete(0);
        //补全数据 4个基础字段信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        //执行业务注册功能实现（row==1） 则不报错
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("在用户注册过程中产生未知异常");
        }

    }

    @Override
    public User login(String username, String password) {
        //根据用户名称来查询用户的数据是否存在，如果不存在则抛出异常
        User result = userMapper.findByUsername(username);
        if (result == null) {
            throw new UserNotFondException("用户数据不存在");
        }
        //检查用户的密码是否匹配
        //1.获取数据库中的加密之后的密码
        String oldPassword = result.getPassword();
        //2.和用户传递过来的密码进行比较
        //2.1先获取盐值，上一次注册时自动生成的盐值
        String salt = result.getSalt();
        //2.2将用户的密码按照相同的MD5算法规则进行加密
        String newMd5Password = getMD5Password(password, salt);
        //将这次登录的加密密码 与库中密码比较
        if (!newMd5Password.equals(oldPassword)) {
            throw new PasswordNotMatchException("用户密码错误");
        }
        //判断is_delete字段是否为1 表示标记被删除
        if (result.getIsDelete()==1) {
            throw new UserNotFondException("用户数据不存在");
        }
        //调用mapper层的findByUsername的数据，set是为了数据量大时提升性能
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        //将当前用户数据返回，返回的数据是为了辅助其它页面做数据展示使用（uid、username、avatar）
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() ==1) {
            throw new UserNotFondException("用户数据不存在");
        }

        //比较页面上的旧密码 和数据库密码是否一致
        String oldMd5Password = getMD5Password(oldPassword, result.getSalt());
        if (!result.getPassword().equals(oldMd5Password)) {
            throw new PasswordNotMatchException("密码错误");
        }
        //将新的密码 加密之后，在更新到库中
        String newMd5Password = getMD5Password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据产生未知的异常");
        }

    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result == null ||result.getIsDelete()==1) {
            throw new UserNotFondException("用户数据不存在");
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    /**
     * user对象中有数据phone、email、gender，手动将uid、username
     * 封装到user对象中
     */
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete()==1) {
            throw new UserNotFondException("用户数据不存在");
        }
        user.setUid(uid);
        user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows= userMapper.updateInfoByUid(user);
        if (rows != 1) {
            throw new UpdateException("更新数据产生未知的异常");
        }

    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete()==1) {
            throw new UserNotFondException("用户数据不存在");
        }
        Integer rows= userMapper.updateAvatarByUid(uid,avatar,username,new Date());
        if (rows != 1) {
            throw new UpdateException("更新用户头像产生未知的异常");
        }
    }

    @Override
    public List<User> queryUserInfo() {
        return userMapper.queryUserInfo();
    }
    @Override
    public List<User> findAllUserByPageF(int pageNum, int pageSize) {
        // TODO Auto-generated method stub
        PageHelper.startPage(pageNum, pageSize);
        List<User> lists = userMapper.queryUserInfo();
        return lists;
    }

    @Override
    public PageInfo<User> findAllUserByPageS(int pageNum, int pageSize) {
        // TODO Auto-generated method stub
        PageHelper.startPage(pageNum, pageSize);
        List<User> lists = userMapper.queryUserInfo();
        PageInfo<User> pageInfo = new PageInfo<User>(lists);
        return pageInfo;

    }




    //定义一个MD5的加密方法
    private String getMD5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            //进行三次加密
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        //返回加密之后的密码
        return password;
    }






}
