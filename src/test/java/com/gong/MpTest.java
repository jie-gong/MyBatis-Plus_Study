package com.gong;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gong.mapper.UserMapper;
import com.gong.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
public class MpTest {
    //继承了baseMapper，所有的方法都来自父类
    @Resource
    private UserMapper userMapper;

    //测试查询全部用户
    @Test
    public void test() {
        //参数是一个wrapper ——————  条件构造器
        List<User> userList = userMapper.selectList(null);
        System.out.println(userList);
        userList.forEach(System.out::println);
    }

    //测试插入
    @Test
    public void insert() {
        User user = new User();
        user.setAge(2);
        user.setName("黄扬");
        user.setEmail("328022056@qq.com");
        for (int i = 0; i < 5; i++) {//批量插入
            int insert = userMapper.insert(user);//帮我们自动生成id
            System.out.println(insert);//结果映射

        }
//        test();
        //发现id会自动回填
    }

    //测试更新
    @Test
    public void update() {
        User user = new User();
        //mybatisP 通过条件自动拼接动态sql
        user.setId(3L);
        user.setEmail("gongjie@qq.com");
        user.setName("公杰");
        user.setAge(21);
        int i = userMapper.updateById(user);
        System.out.println("修改成功" + i);
        if (i == 1) {
            System.out.println("修改成功");
        }
        else {
            System.out.println("修改失败");
        }
    }

    //测试删除
    @Test
    public void delete() {
        userMapper.deleteById(1L);
        System.out.println("删除成功");
    }
    //测试批量删除
    @Test
    public void ListDelete(){
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("name","黄扬");
        int i = userMapper.deleteByMap(objectObjectHashMap);
        System.out.println(i);
        System.out.println("删除成功");
    }
    //测试通过id批量删除
    @Test
    public void ListDeleteById(){
        userMapper.deleteBatchIds(Arrays.asList(12,13));
    }

    @Test
    //测试乐观锁成功
    public void testOptimisticLocker() {
        //1.查询用户信息
        User user = userMapper.selectById(6L);
        //2.修改用户信息
        user.setEmail("12345677890@QQ.com");
        user.setAge(19);
        //3.执行更新操作
        userMapper.updateById(user);
        //乐观锁修改成功

    }


    @Test
    //测试乐观锁失败  多线程下
    public void onTestOptimisticLocker() {
        //线程一
        User user = userMapper.selectById(6L);
        user.setEmail("@QQ.com");
        user.setAge(19);

        //模拟另一个线程 进行插队操作
        User user2 = userMapper.selectById(6L);
        user.setEmail("12345677890@QQ.com");
        user.setAge(19);

        //需要使用自旋锁进行多次提交
        userMapper.updateById(user2);//如果没有乐观锁就会覆盖插队的值

    }

    /**
     * 没有使用乐观锁
     */
    @Test
    //测试查询
    public void Select() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    //测试查询批量查询
    public void SelectListUser() {
        List<User> userList = userMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4));
        userList.forEach(System.out::println);
    }

    @Test
    //条件查询
    /***
     *
     */
    public void SelectById() {
        //创建HashMap数组 自定义查询的条件
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        //自定义要查询的条件
        objectObjectHashMap.put("name", "公杰");
        List<User> userList = userMapper.selectByMap(objectObjectHashMap);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    //测试分页查询
    public void SelectByNon() {
        //参数一 当前页
        //参数二 页面的大小
        Page<User> objectPage = new Page<>(2/**参数一 当前页编号*/, 5/**参数二 当前页显示数量*/);
        userMapper.selectPage(objectPage, null);
        objectPage.getRecords().forEach(System.out::println);
    }


}
