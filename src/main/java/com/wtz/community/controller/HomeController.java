package com.wtz.community.controller;

import com.wtz.community.entity.DiscussPost;
import com.wtz.community.entity.Page;
import com.wtz.community.entity.User;
import com.wtz.community.service.DiscussPostService;
import com.wtz.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：
 * Date:2021/11/12 22:49
 **/
@Controller
public class HomeController {
    @Autowired
    public DiscussPostService discussPostService;

    @Autowired
    public UserService userService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page) {      //返回模板view路径名(方便)，返回mav也可；
        //在方法调用前，MVC会自动实例Model和Page对象，并将page注入到model！
        //所以在thymeleaf中可以直接访问page对象
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");

        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        //以上查到的贴子是id，为了展示，得到用户名
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null) {//将 (发帖用户名，贴子) 存到discussPosts-得到完整的贴子
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);//数据装入model中
        return "/index";    //返回模板view
    }
}
