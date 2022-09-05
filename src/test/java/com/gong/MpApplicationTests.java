package com.gong;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class MpApplicationTests {
    @Resource
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
//        查看默认的数据源  :com.zaxxer.hikari.HikariDataSource

        System.out.println("数据源"+dataSource.getClass());
        //获得数据库连接
//        Connection connection = dataSource.getConnection();
        System.out.println("数据库连接测试"+dataSource.getConnection());

    }

}
