package com.wtz.community.controller;

import com.wtz.community.entity.User;
import com.wtz.community.service.UserService;
import com.wtz.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.PushBuilder;
import java.util.Map;

/**
 * Description：
 * Date:2021/11/15 19:59
 **/

/**
 * 访问注册功能
 */
@Controller
public class LoginController implements CommunityConstant {
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/register",method = RequestMethod.GET)
    public String getRegisterPage() {
        return "/site/register";
    }

    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String getLoginPage() {
        return "/site/login";
    }

    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public String register(Model model, User user) {
        Map<String, Object> map = userService.register(user);
        if (map == null || map.isEmpty()) {     //注册成功！注册时没有问题
            model.addAttribute("msg","我们已经向您的邮箱发送了一封激活邮件，请尽快激活！");
            model.addAttribute("target","/index");
            return "/site/operate-result";
        } else {        //注册时出现问题
            //携带错误信息给注册页面
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            model.addAttribute("emailMsg",map.get("emailMsg"));
            return "/site/register";
        }
    }
    //http://localhost:8080/community/activation/id/code(激活码)
    @RequestMapping(path = "/activation/{userId}/{code}",method = RequestMethod.GET)
    public String activation(Model model, @PathVariable("userId") int userId, @PathVariable("code") String code) {
        int res = userService.activation(userId, code);
        if (res == ACTIVATION_SUCCESS) {
            model.addAttribute("msg","激活成功！您的账号可以正常使用！");
            model.addAttribute("target","/login");
        } else if (res == ACTIVATION_REPEAT) {
            model.addAttribute("msg","该账号已经激活过了！");
            model.addAttribute("target","/index");
        } else {
            model.addAttribute("msg","激活失败！激活码错误！");
            model.addAttribute("target","/index");
        }
        return "/site/operate-result";
    }
}
