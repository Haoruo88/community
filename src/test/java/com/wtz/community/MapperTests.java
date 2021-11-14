package com.wtz.community;

import com.wtz.community.dao.DiscussPostMapper;
import com.wtz.community.dao.UserMapper;
import com.wtz.community.entity.DiscussPost;
import com.wtz.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Description：
 * Date:2021/11/12 16:49
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {
    @Autowired  //Mybatis会通过动态代理来创建实现类
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void test() {
        User user1 = userMapper.selectById(1);
        System.out.println(user1);

        User user2 = new User();
        user2.setUsername("王tt");
        user2.setPassword("wtz11111");
        user2.setEmail("2289146446@qq.com");
        user2.setCreateTime(new Date());
        System.out.println(userMapper.insertUser(user2));
        System.out.println(user2.getId());
    }
    @Test
    public void testDiscussPosts() {
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(0, 0, 10);
        for (DiscussPost dp : discussPosts) {
            System.out.println(dp);
        }
        int rows = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(rows);
    }
}
