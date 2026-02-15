package com.lt.controller100;

import com.lt.VO.LoginVO;
import com.lt.entity100.Users100;
import com.lt.handler100.Result;
import com.lt.jwt.Audience;
import com.lt.jwt.JwtIgnore;
import com.lt.jwt.JwtTokenUtil;
import com.lt.service100.IUsers100Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class Users100Controller {

    @Autowired
    private IUsers100Service usersService;
    @Autowired
    private Audience audience;
    @PostMapping("/login100")
    //这个方法不应该让用户登录
    @JwtIgnore
        //不用登录就有权限
    Result login(@RequestBody LoginVO loginVO)
    {
        Users100 user=usersService.login100(loginVO.getUsername(),loginVO.getPassword()); //验证用户名和密码是否正确
        String token= JwtTokenUtil.createJWT(user.getId(),user.getUsername(),"",audience); //验证成功后创建一个token
        return Result.SUCCESS(token); //返回给用户
    }


}