package com.gong;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gong.mapper.UserMapper;
import com.gong.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest

public class WrapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    //查询name不为空的用户，并且邮箱不为空年龄大于12岁的
    public void WrapperTest(){
        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.isNotNull("name")
                .isNotNull("email")
                .ge("age",12);
        List<User> userList = userMapper.selectList(objectQueryWrapper);
        userList.forEach(System.out::println);
    }
    //精确查询（只能查出一条）
    @Test
    public void ById(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name","娄燕浩");
        //能且只能查询一条数据 ，无法查询多个数据 多条数据使用list后者map查询
        User user1 = userMapper.selectOne(userQueryWrapper);
        System.out.println(user1);
    }
    //查询年龄在20到30岁的用户
    @Test
    public void ByAge(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.between("age",20,30);
        Integer integer = userMapper.selectCount(userQueryWrapper);//查询结果数
        System.out.println(integer);
    }
    //模糊查询
    @Test
    public void NoTest(){
        //查询名字不含‘扬’ 邮箱号开头是‘3’  名字结尾是‘杰‘ 的用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //左和右  %e%
        //左     e%
        //右     %e
        userQueryWrapper.notLike("name","扬")
                .likeRight("email","3")
                .likeLeft("name","杰");
        List<Map<String, Object>> maps = userMapper.selectMaps(userQueryWrapper);
        maps.forEach(System.out::println);
    }
    //测试排序
    @Test
    public void PX(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.orderByAsc("id");
        List<User> userList = userMapper.selectList(userQueryWrapper);
        userList.forEach(System.out::println);
    }
}
