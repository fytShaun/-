package com.fyt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fyt.domain.user;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface userDao extends BaseMapper<user> {
    @Select("SELECT * FROM user \n" +
            "WHERE \n" +
            "    name LIKE #{k} OR \n" +
            "    username LIKE concat('%',#{k},'%') OR \n" +
            "    address LIKE concat('%',#{k},'%') OR \n" +
            "    email LIKE concat('%',#{k},'%') OR \n" +
            "    phone LIKE concat('%',#{k},'%') OR \n" +
            "    id LIKE concat('%',#{k},'%');")
    public List<user> findUsers(String k);
}
