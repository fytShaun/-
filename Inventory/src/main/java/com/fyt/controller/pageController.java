package com.fyt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fyt.domain.goods;
import com.fyt.domain.user;
import com.fyt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class pageController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisToolClass redisToolClass;

    @RequestMapping("/add")
    public String add() {
        return "add";
    }

    @RequestMapping("/login")
    public String test() {
        return "login";
    }

    @RequestMapping("/demo")
    public String test1() {
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "login";
    }
    @ResponseBody
    @GetMapping("/getPage")
    public ModelAndView getPage(String page, ModelAndView model){
        IPage<user> page1 = iUserService.getPage(Integer.parseInt(page), code.page_size);
        List<user> records = page1.getRecords();
        model.setViewName("index");
        model.addObject("usersList",records);
        return model;
    }

    @GetMapping("/getPagegGoods")
    public ModelAndView getPagegGoods(String pageGoods,ModelAndView model){
        List<goods> gooodslist = redisToolClass.getPage(pageGoods);
        model.setViewName("index");
        model.addObject("goodsList",gooodslist);
        return model;
    }
}
