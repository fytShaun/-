package com.fyt;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fyt.dao.userDao;
import com.fyt.domain.goods;
import com.fyt.domain.user;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private userDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
//        redisTemplate.boundValueOps("name").set("xiaoming");
//        Object name = redisTemplate.boundValueOps("name").get();
//        System.out.println(name);
//        goods g = new goods();
//        g.setName("hongshu");
//        g.setId(3);
//        g.setBuyUserName("buy1");
//        redisTemplate.opsForList().leftPush("buy1",g);
        for (goods x:(List<goods>) redisTemplate.opsForList().range("admin2",0,-1)){
            System.out.println(x);
        }
    }

    @Test
    public void s(){
        Page page = new Page(2,3);
        IPage user = userDao.selectPage(page, null);
        System.out.println(user.getRecords());
    }

    @Test
    public void sss(){
//        1:
//        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("username","1");
//        user user = userDao.selectOne(queryWrapper);
//        System.out.println(user);

//        2:
//        LambdaQueryWrapper<user> q = new LambdaQueryWrapper<>();
//        q.like(user::getId,"1");
//        user user = userDao.selectOne(q);
//        System.out.println(user);

//        3:
            String name = "";
            LambdaQueryWrapper<user> q = new LambdaQueryWrapper<>();
            q.like(name!=null,user::getUsername,name);
            user user = userDao.selectOne(q);
            System.out.println(user);
    }

}
