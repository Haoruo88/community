package com.wtz.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Description：
 * Date:2021/11/10 20:50
 **/
@Controller
@RequestMapping("/demo")
public class demo {
    @RequestMapping("hello")
    @ResponseBody
    public String sayHello() {
        return "Hello !";
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        System.out.println(httpServletRequest.getMethod());
        System.out.println(httpServletRequest.getServletPath());
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String s = headerNames.nextElement();
            String value = httpServletRequest.getHeader(s);
            System.out.println(s + ":" + value);
        }
        System.out.println(httpServletRequest.getParameter("code"));

        //响应
        httpServletResponse.setContentType("text/html;charset=utf-8");
        try (
                PrintWriter writer = httpServletResponse.getWriter();
                ) {
            writer.write("论坛");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   //(默认都是GET方法请求)
    //获取请求参数
        //参数以 ?xx & xx 的形式
    //students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            //详细的获取指定请求参数
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit
    ) {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }
    //参数以 / 的形式
    //students/123
    @RequestMapping(value = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }
    //POST请求
    @RequestMapping(value = "/student",method = RequestMethod.POST)
    @ResponseBody
    //方法参数和表单提交的参数名字一致就可以传过来
    public String addStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return "success!";
    }
    //1.响应动态的html 不加@ResponseBody了
    @RequestMapping(value = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "张三");    //添加实体内容
        mav.addObject("age",30);
        mav.setViewName("/demo/teacherView");   //添加视图模板(在templates下)
        return mav;
    }
    //2.前端控制器调用该方法时，会自动实例化一个model对象并传给控制器存储数据，最后前端控制器再封装view和数据
    @RequestMapping(value = "/teacher2",method = RequestMethod.GET)
    public String getTeacher2(Model model) {
        model.addAttribute("name","李四");
        model.addAttribute("age","40");
        return "/demo/teacherView";//view模板
    }
    //响应JSON数据(适用于异步请求) ：方便跨语言转换 Java对象->JSON字符串->JS对象
    //异步：浏览器不用等待服务器处理请求，不用重新加载页面来展示服务器返回的数据；
    //  在异步请求的发送过程中浏览器可以执行别的操作
    @RequestMapping(value = "/emp",method = RequestMethod.GET)
    @ResponseBody   //自动转化为JSON字符串
    public Map<String, Object> getEmp() {
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "李达");
        emp.put("age", 40);
        emp.put("salary", 6000);
        return emp;
    }
    //多个对象的集合
    @RequestMapping(value = "/emps",method = RequestMethod.GET)
    @ResponseBody   //自动转化为JSON字符串
    public List<Map<String, Object>> getEmps() {
        List<Map<String, Object>> emps = new ArrayList<>();

        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "李达");
        emp.put("age", 40);
        emp.put("salary", 6000);
        emps.add(emp);

        emp = new HashMap<>();
        emp.put("name", "李浩");
        emp.put("age", 20);
        emp.put("salary", 10000);
        emps.add(emp);

        return emps;
    }
}
