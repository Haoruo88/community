package com.wtz.community.service;

import com.wtz.community.dao.UserMapper;
import com.wtz.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description：
 * Date:2021/11/12 22:24
 **/
@Service
public class UserService {

    @Autowired
    public UserMapper userMapper;

    public User findUserById(int id) {
        return userMapper.selectById(id);
    }
}
