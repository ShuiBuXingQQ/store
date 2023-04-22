package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.ex.ServiceException;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest  //表示当前类是一个测试类，不会随项目一块打包
@RunWith(SpringRunner.class) //表示启动这个单元测试类（单元测试类是不能够运行的），需要传递一个参数，必须是Spring Runner的实例类型
public class IUserServiceTest {
    @Autowired    //idea有检查功能，接口是不能直接创建Bean的（动态代理技术来解决）
    private IUserService UserService;

    /**
     * 单元测试方法：可以单独独立运行，不用启动整个项目，提升代码的测试效率
     * 1.必须@Test注解修饰
     * 2.返回值类型必须是viod
     * 3.方法参数不指定任何类型
     * 4.方法的访问修饰符必须是public
     */
    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("zhangsan04");
            user.setPassword("123");
            UserService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            //获取类的对象，获取类的名字
            System.out.println(e.getClass().getSimpleName());
            //获取异常的具体描述信息
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void login() {
        User user = UserService.login("zhangsan04", "123");
        System.out.println(user);

    }

    @Test
    public void changePassword() {
        UserService.changePassword(7, "zhangsan04", "123", "321");

    }

    @Test
    public void getByUid() {
        UserService.getByUid(1);
    }

    @Test
    public void changeInfo() {
        User user = new User();
        user.setPhone("123456");
        user.setEmail("123@163.com");
        user.setGender(1);
        UserService.changeInfo(1, "张三三", user);
    }



    @Test
    public void changeAvatar(){
        UserService.changeAvatar(1, "12com", "zhansan");
    }



    @Test
    public void queryUserInfo(){
        List<User> users = UserService.queryUserInfo();
        System.out.println(users);
    }
    @Test
    public void findAllUserByPageF(){
        List<User> allUserByPageF = UserService.findAllUserByPageF(1, 2);
        System.out.println(allUserByPageF);
    }
    @Test
    public void findAllUserByPageS(){
        PageInfo<User> allUserByPageS = UserService.findAllUserByPageS(1, 2);
        System.out.println(allUserByPageS);
    }



}

