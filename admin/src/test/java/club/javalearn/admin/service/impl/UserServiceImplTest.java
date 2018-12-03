package club.javalearn.admin.service.impl;

import club.javalearn.admin.AdminApplicationTests;
import club.javalearn.admin.entity.User;
import club.javalearn.admin.entity.info.UserInfo;
import club.javalearn.admin.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;



public class UserServiceImplTest extends AdminApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void findByUserName() {

    }

    @Test
    public void findByUserId() {

    }

    @Test
    public void save() {
        User user = new User();
        user.setCreateTime(new Date());
        user.setLastLoginTime(new Date());
        user.setUpdateTime(new Date());
        user.setUserName("pwpw1218");
        user.setNickName("king-pan");
        user.setEmail("4677444@qq.com");
        user.setPhoneNum("1800000000000");
        userService.save(new UserInfo(user));
    }

    @Test
    public void deleteRole() {
    }

    @Test
    public void updateLastLoginTime() {
    }

    @Test
    public void addRole() {
    }

    @Test
    public void getPageList() {
    }

    @Test
    public void deleteUsers() {
    }
}