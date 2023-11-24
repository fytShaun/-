package com.fyt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fyt.domain.user;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional  //加事务
public interface IUserService extends IService<user> {
    public IPage<user> getPage(int page,int size);
    public List<user> findUsers(String k);
}
