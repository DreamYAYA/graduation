package com.lql.graduation.service;

import com.lql.graduation.pojo.User;

public interface UserService {

    public int regist(User user);

    User login(User user);
}
