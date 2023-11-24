package com.fyt.controller;

import com.fyt.domain.goods;
import com.fyt.domain.user;
import com.fyt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class RedisToolClass {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private RedisTemplate redisTemplate;


    public List<goods> getPage(String pageGoods){
        System.out.println(iUserService);
        List<user> list = iUserService.list();
        int sum=0;
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
            for(goods g:range){
                sum++;
                if(sum>(Integer.parseInt(pageGoods)-1)*code.page_size){
                    gooodslist.add(g);
                    if(gooodslist.size()>=code.page_size){
                        break;
                    }
                }
            }
            if(gooodslist.size()>=code.page_size){
                break;
            }
        }
        return gooodslist;
    }

    public void addGoodsNum(String key,goods goods){
        List<goods> range =(List<goods>) redisTemplate.opsForList().range(key, 0, -1);
        int index=-1;
        int n=goods.getAddGoodsNumber();
        for (goods x:range){
            index++;
            if(goods.getName().equals(x.getName())){
                break;
            }
        }
        String datef = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        goods.setInventories(Math.max(goods.getInventories()+goods.getAddGoodsNumber(),0));
        goods.setUpdateTime(datef);
        redisTemplate.opsForList().set(key,index,goods);
    }
}
