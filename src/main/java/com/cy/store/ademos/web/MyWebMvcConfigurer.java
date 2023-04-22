/*
package com.cy.store.demos.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

*/
/**
 * @author: 轻率的保罗
 * @since: 2023-01-16
 * @Description: 静态资源规则配置。配置 全局异常处理-404异常捕获 之后（所有访问不到的路径，均会抛出404异常，捕获之后返回自定义的DataVo实例-json对象），导致静态资源无法正常访问，添加如下配置。
 * 注意：若访问路径（静态资源），从以下addResourceHandler配置的目录中找不到，则会返回“Whitelabel Error Page”页面，而不是经过全局异常处理捕获后返回的404错误信息（DataVo对象）
 *//*

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态资源：
        //    前端网站，访问 路径/web/** ，会在 项目resources/static/web/ 目录下查找
        //    文件，访问 路径/public/file/** ，会在 项目resources/static/public/file/ 目录下查找
        //    放图片，访问 路径/public/** 下的静态资源，会在 项目resources/static/public/image/ 目录下查找
        registry.addResourceHandler("/web/**").addResourceLocations("classpath:/static/web/");
        registry.addResourceHandler("/public/file/**").addResourceLocations("classpath:/static/public/file/");
        registry.addResourceHandler("/public/image/**").addResourceLocations("classpath:/static/public/image/");
        //swaggerUI：
        //    swagger第三方UI - Knife4j，访问地址为：http://localhost:8181/malldemo/doc.html
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");//以jar包方式访问js、css、html等静态资源（Knife4j的相关依赖已打包成jar包）
    }
}
*/
