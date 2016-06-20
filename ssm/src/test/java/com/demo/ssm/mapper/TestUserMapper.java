package com.demo.ssm.mapper;

import com.demo.ssm.po.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2015/11/28.
 */
public class TestUserMapper {

    private ApplicationContext applicationContext;

    @Before
    public void setUp() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
    }

    @Test
    public void testAdd()  throws Exception {
        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        User user = new User();
        UUID uuid = UUID.randomUUID();
        user.setId(uuid.toString());
        user.setUname("admin");
        user.setName("管理员1");
        user.setPwd("123");
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String created_time = dateFormat.format(now);
        System.out.print(created_time);
        Timestamp time = new Timestamp(now.getTime());
        user.setCreate_time(time);
        user.setBirthday(new java.util.Date(1982, 8, 19));
        userMapper.add(user);
        System.out.print(user);
    }
}
