package com.hao.mycenter2.service;
import java.util.Date;

import com.hao.mycenter2.model.Usercenter;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/*
* 用户服务测试
*
* @author:awei
* */
@SpringBootTest
class UsercenterServiceTest {

    @Resource
    private UsercenterService usercenterService;

    @Test
    public void testAddUser(){
        Usercenter user = new Usercenter();
        user.setId(0L);
        user.setUsername("awei");
        user.setAvatarurl("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089f1fd98b1256b5.png");
        user.setGender(1);
        user.setUserpassword("1886989746464");
        user.setPhone("234");
        user.setEmail("234");
        user.setIsvalid("222");
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
        user.setIsdeleted(0);
        boolean result = usercenterService.save(user);
        System.out.println(user.getId());
        Assertions.assertEquals(true,result);

    }

    @Test
    void userRegister() {
        String userAccount = "awei";
        String userPassword = "";
        String checkPassword = "123456";
        long result = usercenterService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,result);
        userPassword = "123456789";
        userAccount = "qw";
        result = usercenterService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1,result);
        userAccount = "awei";
        checkPassword = "123456789";
        result = usercenterService.userRegister(userAccount, userPassword, checkPassword);
        userAccount = "awerboke";
        result = usercenterService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertTrue(result>0);

    }
}
