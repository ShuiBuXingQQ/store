package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.ServiceException;
import com.cy.store.service.ex.UsernameDuplicateException;
import com.cy.store.util.JsonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// http://127.0.0.1:8080/users/reg?username=name01&password=123


//@Controller
@Api(value = "用户信息",tags = "用户信息API")
@RestController //@Controller+@ResponseBody
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;


    /**
     * 1.接受数据方式：请求处理方法的参数列表设置为pojo类型来接受前端数据
     * Spring boot会将前端的url地址中的参数名和pojo类的属性名进行比较，
     * 如果这俩个地方名称一致，则将值注入到pojo类中对应的属性上
     */

    @PostMapping("reg")
//    @ResponseBody //表示此方法的响应结果以json格式的数据响应 给前端
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    /**
     * 2.接受数据方式：请求处理方法的参数列表设置为 非pojo类型
     * Spring boot会直接将请求的参数名和方法的参数名直接进行比较
     * 如果名称相同则自动完成值的依赖注入
     */

    @ApiOperation(value = "用户登录", httpMethod = "POST",notes = "用户的登录notes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号", required = true, dataType = "String", paramType = "Query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "Query"),
            })
    @PostMapping("login")
    public JsonResult<User> login(@RequestParam("username")String username, @RequestParam("password")String password, HttpSession session) {
        User data = userService.login(username, password);
        //向session对象中完成数据的绑定（session全局的）
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        //获取session中的绑定数据
        System.out.println(getuidFromSession(session));
        System.out.println(getUsernameFromSession(session));

        return new JsonResult<User>(OK, data);
    }

    @ApiOperation(value = "密码修改", httpMethod = "POST",notes = "密码修改notes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword", value = "旧密码", required = true, dataType = "String", paramType = "Query"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "String", paramType = "Query"),
    })
    @PostMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }

    @GetMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        User data = userService.getByUid(getuidFromSession(session));
        return new JsonResult<>(OK, data);
    }

    @PostMapping("change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        //user 对象有四部分数据：username，phone，Email，gender
        //uid 数据需要再次封装到user对象中
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid, username, user);
        return new JsonResult<>(OK);
    }

    /**设置上传文件最大值 10M */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    /**
     * 设置上传文件类型
     */
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    /**
     * MultipartFile 接口是SpringMVC提供的接口，这个接口为我们包装了
     * 获取文件类型的数据（任何类型的file都可以接收），spring boot又整合了spring MVC，
     * 只需要在请求处理的方法参数列表上声明一个参数类型为MultipartFile 的参数，
     * 然后spring boot自动将传递给服务的文件数据赋值给这个参数
     * @RequestParam 表示请求中的参数，将请求中的参数注入请求处理方法的某个参数上
     * （即前端发送请求的参数不是后端想要的），后端可以用该注解进行 参数的标记和映射
     * @param file
     * @param session
     * @return
     */
    @PostMapping("change_avatar")
    public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file,
                                           HttpSession session) {
        //判断文件是否为null
        if (file.isEmpty()) {
            throw new FileEmptyException("文件为空");
        }
        //判断文件大小
        if (file.getSize()>AVATAR_MAX_SIZE) {
            throw new FileSizeException("文件大小超出限制");
        }
        //判断文件类型是否为我们规定的后缀类型
        String contentType = file.getContentType();
        //如果集合包含某个元素则返回值true
        if (!AVATAR_TYPE.contains(contentType)) {
            throw new FileTypeException("文件类型不支持");
        }
        //上传的文件.../upload/文件.png
        String parent = session.getServletContext().getRealPath("upload");
        //file对象指向这个路径，file是否存在
        File dir = new File(parent);
        if (!dir.exists()) { //检查目录是否存在
            dir.mkdirs(); //创建当前目录
        }
        //获取到这个文件名称，uuid工具来将一个新的字符串作为文件名
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;
        File dest = new File(dir, filename);//是一个空文件
        //参数file中的数据写入这个空文件中
        try {
            file.transferTo(dest); //将file文件中的数据写道dest文件
        } catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }

        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        //返回头像的路径、/upload/ +filename
        String avatar = "/upload/" + filename;
        userService.changeAvatar(uid, avatar, username);
        //返回用户头像的路径给前端页面，将来用于图像展示
        return new JsonResult<>(OK,avatar);
    }

    //分页有统计版
    @GetMapping("/testPageHelper1")
    public PageInfo<User> testPageHelper1(int pageNum,int pageSize){
        PageInfo<User> data = userService.findAllUserByPageS(pageNum, pageSize);
        return data;
    }
    //分页部分版
    @GetMapping("/testPageHelper2")
    public List<User> testPageHelper2(int pageNum,int pageSize){
        List<User> data = userService.findAllUserByPageF(pageNum, pageSize);
        return data;
    }
    //分页最终版
    @GetMapping("testPageHelper3")
    public JsonResult<PageInfo<User>> testPageHelper3(int pageNum,int pageSize) {
        PageInfo<User> data = userService.findAllUserByPageS(pageNum, pageSize);
        return new JsonResult<PageInfo<User>>(OK, data);
    }


/*
  @RequestMapping("reg")
//    @ResponseBody //表示此方法的响应结果以json格式的数据响应 给前端
    public JsonResult<Void> reg(User user){
        //创建响应结果对象
        JsonResult<Void> result = new JsonResult<>();
        try {
            userService.reg(user);
            result.setState(200);
            result.setMessage("用户注册成功");
        } catch (UsernameDuplicateException e) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        }catch (InsertException e) {
            result.setState(5000);
            result.setMessage("注册时产生未知异常");
        }
        return result;
    }
*/


}
