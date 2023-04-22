package com.cy.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StoreApplicationTests {
    @Autowired//自动装配
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }


    //HikariProxyConnection 数据库链接池，管理数据库的链接对象
    @Test
    void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}
