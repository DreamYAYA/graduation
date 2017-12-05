package com.lql.graduation.service.serviceImpl;

import com.lql.graduation.mapper.UserMapper;
import com.lql.graduation.pojo.User;
import com.lql.graduation.service.UserService;
import com.lql.graduation.util.ResponseCode;
import com.lql.graduation.util.UidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Override
    public int regist(User user) {

        try {

            String UUID = UidUtils.getUid();
            user.setUserId(UUID);
            //设置注册时间,以及注册人
            user.setCreateBy(UUID);
            user.setCreateTime(new Date());
            userMapper.insert(user);
             return ResponseCode.SUCCESS.getCode();
        }catch (Exception e){

            e.printStackTrace();
            return ResponseCode.ERROR.getCode();
        }

    }

    /**
     *
     * 登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {

        User loginUser = userMapper.queryUserByNameAndPass(user);

        return loginUser;
    }




}
