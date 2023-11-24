package com.fyt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fyt.dao.buyDao;
import com.fyt.dao.saleDao;
import com.fyt.domain.buy;
import com.fyt.domain.goods;
import com.fyt.domain.sale;
import com.fyt.domain.user;
import com.fyt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/goods")
public class goodsController {

    private String[] p={"供应商A","供应商B","供应商C"};
    @Autowired
    private buyDao buyDao;

    @Autowired
    private saleDao saleDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private RedisToolClass redisToolClass;

    @PostMapping("/ModifyGoods")
    public result ModifyGoods(@RequestBody goods goods){
        String datef = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        goods.setUpdateTime(datef);
        String kk = goods.getBuyUserName();
        user user = iUserService.getById(kk);
        String identity = user.getIdentity();
        String k="";

        if("1".equals(identity)){
            k = "admin"+kk;
        }else if("2".equals(identity)){
            k = "buy"+kk;
        }else {
            k = "sale"+kk;
        }
//        List<user> list = iUserService.list();
        List<goods> goodsllist =(List<goods>) redisTemplate.opsForList().range(k, 0, -1);
        int index = -1;
        for(goods g:goodsllist){
            index++;
            if(g.getName().equals(goods.getName())){
                break;
            }
        }
//        index = goodsllist.size()-index-1;
        redisTemplate.opsForList().set(k,index,goods);
        return new result("ok",code.update_ok,"ok");
    }

    @PostMapping("/addGoods")
    public result addGoods(@RequestBody goods goods){
        String datef = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        goods.setUpdateTime(datef);
        String buyUserName = goods.getBuyUserName();
        user user = iUserService.getById(buyUserName);
        String identity = user.getIdentity();
        String k="";
        if("1".equals(identity)){
            k = "admin"+buyUserName;
        }else if("2".equals(identity)){
            k = "buy"+buyUserName;
        }else {
            k = "sale"+buyUserName;
        }
        buy buy = new buy();
        buy.setGoodsName(goods.getName());
        buy.setNumber(goods.getInventories());
        buy.setPrice(goods.getPurchasePrice());
        buy.setUnit(goods.getUnit());
        buy.setUserId(goods.getBuyUserName());
        Random random = new Random();
        int x = random.nextInt(3);
        buy.setProvider(p[x]);
        buyDao.insert(buy);
        redisTemplate.opsForList().leftPush(k,goods);
        return new result("ok",code.save_ok,"ok");
    }

    @PostMapping("/deleteGoods")
    private result deleteGoods(@RequestBody goods goods){
        String userId = goods.getBuyUserName();
        user byId = iUserService.getById(userId);
        String key="";
        if(byId.getIdentity().equals("1")){
            key="admin"+userId;
        }else if(byId.getIdentity().equals("2")){
            key="buy"+userId;
        }else{
            key="sale"+userId;
        }
        String goodsName = goods.getName();
        List<user> list = iUserService.list();
        List<goods> goodsllist =(List<goods>) redisTemplate.opsForList().range(key, 0, -1);
        int index = -1;
        for(goods g:goodsllist){
            index++;
            if(g.getName().equals(goodsName)){
                break;
            }
        }
        redisTemplate.opsForList().remove(key,1,goods);

        return new result("",code.delete_ok,"ok");
    }

    @GetMapping("/getGoodsByName")
    public ModelAndView getGoodsByName(String SearchKey, ModelAndView model){
        List<user> list = iUserService.list();
        String k="";
        ArrayList<goods> gooodslist = new ArrayList<>();
        for(user u: list){
            String identity = u.getIdentity();
            int id = u.getId();
            if("1".equals(identity)){
                k="admin"+id;
            }else if("2".equals(identity)){
                k="buy"+id;
            }else {
                k="sale"+id;
            }
            List<goods> range =(List<goods>) redisTemplate.opsForList().range(k, 0, -1);
            for(goods x:range){
                String name = x.getName();
                if(x.getName().contains(SearchKey)){
                    gooodslist.add(x);
                }
            }
        }
        System.out.println(gooodslist);
        model.addObject("goodsList",gooodslist);
        model.setViewName("index");
        return model;
    }

    @PostMapping("/addGoodsNum")
    public result addGoodsNum(@RequestBody goods goods, HttpSession session){
        String key = goods.getBuyUserName();
        LambdaQueryWrapper<user> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(key!=null,user::getId,key);
        user one = iUserService.getOne(lambdaQueryWrapper);
        if("1".equals(one.getIdentity())){
            key="admin"+key;
        }else if("2".equals(one.getIdentity())){
            key="buy"+key;
        }else{
            key="sale"+key;
        }
        if(goods.getAddGoodsNumber()>0) {
            buy buy = new buy();
            buy.setGoodsName(goods.getName());
            buy.setNumber(goods.getAddGoodsNumber());
            buy.setPrice(goods.getPurchasePrice());
            buy.setUnit(goods.getUnit());
            buy.setUserId(goods.getBuyUserName());
            Random random = new Random();
            int x = random.nextInt(3);
            buy.setProvider(p[x]);
            buyDao.insert(buy);
        }
        else{
            sale sale = new sale();
            sale.setGoodsName(goods.getName());
            sale.setNumber(-goods.getAddGoodsNumber());
            sale.setPrice(goods.getSellPrice());
            sale.setUnit(goods.getUnit());
            sale.setUserId(goods.getBuyUserName());
            Random random1 = new Random();
            int xx = random1.nextInt(3);
            sale.setProvider(p[xx]);
            saleDao.insert(sale);
        }

        System.out.println(key);
        redisToolClass.addGoodsNum(key,goods);
        return new result();
    }
}
