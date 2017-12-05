package com.lql.graduation.controller.user;

import com.lql.graduation.pojo.User;
import com.lql.graduation.service.UserService;
import com.lql.graduation.util.ResponseCode;
import com.lql.graduation.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *
     * 注册
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/regist")
     public ServerResponse regist(User user){

        int resultCode = userService.regist(user);

        if(ResponseCode.SUCCESS.getCode()==resultCode){
               return ServerResponse.createBySuccess();
        }else{
            return ServerResponse.createByError();
        }

     }

    /**
     * 登录
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public ServerResponse login(User user){
        User loginUser = userService.login(user);
        if(loginUser!=null){
            return ServerResponse.createBySuccessMessage("登录成功",loginUser);
        }else{
            return ServerResponse.createByErrorMessage("用户名或者面错误");
        }
    }





}
