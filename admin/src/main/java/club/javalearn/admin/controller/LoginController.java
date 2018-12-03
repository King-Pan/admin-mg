package club.javalearn.admin.controller;

import club.javalearn.admin.common.ServerResponse;
import club.javalearn.admin.entity.User;
import club.javalearn.admin.service.UserService;
import club.javalearn.admin.utils.JwtUtil;
import club.javalearn.admin.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @author king-pan
 * Date: 2018-12-03
 * Time: 17:10
 * Description: No Description
 */
@RestController
public class LoginController {


    @Autowired
    private UserService userService;


    @Autowired
    private PasswordHelper passwordHelper;

    @PostMapping("/login")
    public ServerResponse login(@RequestParam("username") String username,
                                @RequestParam("password") String password) {

        User user = userService.findByUserName(username);
        System.out.println(user);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(401, "用户名密码错误");
        }

        //密码匹配成功
        if (passwordHelper.matchPassword(password, user)) {
            return ServerResponse.createBySuccess("登录成功", JwtUtil.sign(username, user.getPassword()));
        } else {
            return ServerResponse.createByErrorCodeMessage(401, "用户名密码错误");
        }
    }
}
