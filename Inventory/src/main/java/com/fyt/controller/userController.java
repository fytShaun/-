package com.fyt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fyt.dao.buyDao;
import com.fyt.dao.saleDao;
import com.fyt.domain.*;
import com.fyt.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class userController {
    @Autowired
    private saleDao saleDao;

    @Autowired
    private buyDao buyDao;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisToolClass redisToolClass;

    @RequestMapping("/doLogin")
    public result doLogin(@RequestBody user u, HttpSession session){
        String name = u.getUsername();
        LambdaQueryWrapper<user> q = new LambdaQueryWrapper<>();
        q.like(name!=null,user::getUsername,name);
        user o = iUserService.getOne(q);
        if(o==null){
            return new result(null,code.get_err,"输入错误");
        }
        if(o.getState().equals("0")){
            return new result(null,code.get_err,"账号禁用");
        }
        boolean ok =o.getPassword().equals(u.getPassword());
        if(ok){
            session.setAttribute("user",o);
        }
        return new result(ok?o.getIdentity():null,ok?code.get_ok:code.get_err,ok?"登陆成功":"请重试");
    }


    @GetMapping("/doLoginOk/{id}")
    public ModelAndView doLoginOk(@PathVariable Integer id, ModelAndView modelAndView,HttpSession session){
        QueryWrapper<user> query= new QueryWrapper<>();
//        query.last("LIMIT 5");
        List<user> list = iUserService.list(query);
        List<user> list1 = list.subList(0, Math.min(code.page_size,list.size()));

        modelAndView.addObject("usersList",list1);
        int count = iUserService.count();
        int UsersPageNumber = count / code.page_size + (count % code.page_size == 0 ? 0 : 1);;
        session.setAttribute("UsersPageNumber",UsersPageNumber);

        ArrayList<goods> goodsllist = new ArrayList<>();
        for(user x : list){
            String identity = x.getIdentity();
            String k="";
            if("1".equals(identity)){
                k = "admin"+x.getId();
            }else if("2".equals(identity)){
                k = "buy"+x.getId();
            }else {
                k = "sale"+x.getId();
            }
            List<goods> range =(List<goods>) redisTemplate.opsForList().range(k, 0, -1);
            goodsllist.addAll(range);
        }
        int GoodsPageNumber = goodsllist.size() / code.page_size + (goodsllist.size() % code.page_size == 0 ? 0 : 1);
        session.setAttribute("GoodsPageNumber",GoodsPageNumber);

        modelAndView.addObject("goodsList",goodsllist);
        if(id==1){
            modelAndView.setViewName("index");
        }
        else if(id==2) modelAndView.setViewName("indexBuy");
        else modelAndView.setViewName("indexSale");

        List<buy> buys = buyDao.selectList(null);
        modelAndView.addObject("buyList",buys);

        List<sale> sales = saleDao.selectList(null);
        modelAndView.addObject("saleList",sales);

        return modelAndView;
    }
    @PostMapping("/ModifyPassword")
    public result ModifyPassword(@RequestBody pass pass, HttpSession session){
        user user =(user) session.getAttribute("user");
        boolean a = user.getPassword().equals(pass.getOld());
        boolean c = pass.getNew1().equals(pass.getNew2());
        if(a&&c){
            user.setPassword(pass.getNew1());
            boolean b = iUserService.updateById(user);
            return new result(b,b?code.update_ok:code.update_err,b?"修改成功！":"修改失败！内部错误，请联系管理员！");
        }else{
            return new result(false,code.update_err,"输入错误,请检查后重新输入");
        }
    }

    @PostMapping("/ModifyUser")
    public result ModifyUser(@RequestBody user user){
        boolean b = iUserService.updateById(user);
        return new result(b,b?code.update_ok:code.update_err,b?"更新成功":"更新失败");
    }

    @GetMapping("/deleteUser/{id}")
    public result deleteUser(@PathVariable int id){
        boolean b = iUserService.removeById(id);
        return new result(b,b?code.delete_ok:code.delete_err,b?"删除成功":"删除失败");
    }
    @PostMapping("/addUser")
    public result addUser(@RequestBody user user){
        boolean b = iUserService.save(user);
        return new result(b,b?code.save_ok:code.save_err,b?"添加成功":"添加失败");
    }

    @GetMapping("/findUsers")
    public ModelAndView findUsers(String k,ModelAndView modelAndView){
        List<user> users = iUserService.findUsers(k);
        modelAndView.addObject("usersList",users);
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
