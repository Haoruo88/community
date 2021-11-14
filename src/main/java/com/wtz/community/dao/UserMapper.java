package com.wtz.community.dao;

import com.wtz.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description：
 * Date:2021/11/12 15:28
 **/
@Mapper     //数据库访问组件
public interface UserMapper {   //user表
    User selectById(int id);    //根据id查询用户

    User selectByName(String name);

    User selectByEmail(String email);

    int insertUser(User user);      //插入用户

    int updateStatus(int id, int status);   //更新用户信息

    int updateHeaderUrl(int id, String headerUrl);

    int updatePassword(int id, String password);
}
