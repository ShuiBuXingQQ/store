package com.cy.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//注解指定当前项目中的Mapper接口路径位置，在项目启动的时候会自动加载所有接口
@EnableSwagger2// 开启Swagger2的自动配置
@MapperScan("com.cy.store.mapper")
public class StoreApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(StoreApplication.class, args);
            System.out.println("server startup done");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("服务启动报错");

        }

    }

}
