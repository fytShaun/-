package com.fyt.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fyt.dao.userDao;
import com.fyt.domain.user;
import com.fyt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp extends ServiceImpl<userDao,user> implements IUserService {
    @Autowired
    private userDao userDao;
    @Override
    public IPage<user> getPage(int page, int size) {
        IPage p = new Page(page,size);
        userDao.selectPage(p,null);
        return p;
    }

    @Override
    public List<user> findUsers(String k) {
        return userDao.findUsers(k);
    }
}
