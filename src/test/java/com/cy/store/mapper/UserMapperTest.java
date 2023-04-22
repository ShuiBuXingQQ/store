package com.cy.store.mapper;

import com.cy.store.entity.User;
import com.github.pagehelper.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest  //表示当前类是一个测试类，不会随项目一块打包
@RunWith(SpringRunner.class) //表示启动这个单元测试类（单元测试类是不能够运行的），需要传递一个参数，必须是Spring Runner的实例类型
public class UserMapperTest {
    @Autowired    //idea有检查功能，接口是不能直接创建Bean的（动态代理技术来解决）
    private UserMapper userMapper;

    /**
     * 单元测试方法：可以单独独立运行，不用启动整个项目，提升代码的测试效率
     * 1.必须@Test注解修饰
     * 2.返回值类型必须是viod
     * 3.方法参数不指定任何类型
     * 4.方法的访问修饰符必须是public
     */
    @Test
    public void insert() {
        User user = new User();
        user.setUsername("zhangsan3");
        user.setPassword("123");
        Integer row = userMapper.insert(user);
        System.out.println(row);
    }

    @Test
    public void findByUsername() {
        User user = userMapper.findByUsername("zhangsan");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid() {
        Integer row = userMapper.updatePasswordByUid(2, "1233", "zhangsansan", new Date());
        System.out.println(row);
    }



    @Test
    public void findByUid() {
        User user = userMapper.findByUid(1);
        System.out.println(user);
    }

    @Test
    public void updateInfoByUid() {
        User user = new User();
        user.setUid(1);
        user.setPhone("123456789");
        user.setEmail("123@163.com");
        user.setGender(1);
        Integer row = userMapper.updateInfoByUid(user);
        System.out.println(row);
    }


    @Test
    public void updateAvatarByUid() {
        Integer row = userMapper.updateAvatarByUid(1,"000","com",new Date());
        System.out.println(row);
    }

    @Test
    public void queryUserInfo() {
        List<User> users = userMapper.queryUserInfo();
        System.out.println(users);
    }


}

