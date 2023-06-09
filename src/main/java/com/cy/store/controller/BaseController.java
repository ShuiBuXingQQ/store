package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

//控制层类的基类
public class BaseController {
    //操作成功的状态码
    public static final int OK = 200;

    //请求处理方法，这个方法的返回值就是需要传递给全端的数据
    //自动将异常对象传递给此方法的参数列表上
    //当前项目产生了异常，被统一拦截到此方法中，这个方法此时就充当异常请求处理方法，方法的返回值直接给到前端
    @ExceptionHandler({ServiceException.class,FileUploadException.class}) //用于统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicateException) {
            result.setState(4000);
            result.setMessage("用户名已被占用");
        }else if (e instanceof AddressNotFoundException) {
            result.setState(4001);
            result.setMessage("用户收获地址不存在异常");
        }else if (e instanceof AccessDeniedException) {
            result.setState(4002);
            result.setMessage("用户收获地址非法访问异常");
        }else if (e instanceof AddressCountLimitException) {
            result.setState(4003);
            result.setMessage("用户收获地址超出上限异常");
        } else if (e instanceof DeleteException) {
            result.setState(5004);
            result.setMessage("数据删除时产生未知异常");
        }else if (e instanceof UpdateException) {
            result.setState(5003);
            result.setMessage("数据更新时产生未知异常");
        }else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("用户密码不正确");
        }else if (e instanceof UserNotFondException) {
            result.setState(5001);
            result.setMessage("用户数据不存在");
        }else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("注册时产生未知的异常");
        }else if (e instanceof FileUploadIOException) {
            result.setState(6004);
            result.setMessage("文件读写时产生异常");
        }else if (e instanceof FileTypeException) {
            result.setState(6003);
            result.setMessage("文件类型不合法产生异常");
        }else if (e instanceof FileStateException) {
            result.setState(6002);
            result.setMessage("文件上传状态不合法产生异常");
        }else if (e instanceof FileSizeException) {
            result.setState(6001);
            result.setMessage("文件大小超出限制产生异常");
        }else if (e instanceof FileEmptyException) {
            result.setState(6000);
            result.setMessage("文件为空产生异常");
        }
        return result;
    }

    /**
     * 获取session对象的uid
     * @param session session对象
     * @return 当前登录用户的uid值
     */
    protected final Integer getuidFromSession(HttpSession session){
       return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取当前登录用户的username
     * @param session session对象
     * @return 当前登录用户的用户名
     */
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }


}
