package com.fyt.service.impl;

import com.fyt.service.goodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class goodsServiceImp  implements goodsService {
    @Autowired
    private RedisTemplate redisTemplate;


}
