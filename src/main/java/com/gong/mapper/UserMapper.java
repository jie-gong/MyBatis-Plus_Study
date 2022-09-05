package com.gong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gong.pojo.User;
import org.springframework.stereotype.Repository;


@Repository//持久层框架
//在对应的Mapper上继承基本的类baseMapper
public interface UserMapper extends BaseMapper<User> {
    //所有的CRUD已经编写完成
    //不需要像以前的配置一些xml

}

