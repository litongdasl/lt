package com.lt.service.impl100;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.entity100.Users100;
import com.lt.handler100.CustomException;
import com.lt.handler100.ResultCode;
import com.lt.mapper100.Users100Mapper;
import com.lt.service100.IUsers100Service;
import org.springframework.stereotype.Service;

@Service
public class Users100ServiceImpl extends ServiceImpl<Users100Mapper, Users100> implements IUsers100Service {

    @Override
    public Users100 login100(String username, String password) {
        QueryWrapper<Users100> qw=new QueryWrapper<>();
        qw.eq("username",username); //先查一下有没有这个用户
        Users100 user=this.getOne(qw);
        if(user==null) //用户名不存在
            throw new CustomException(ResultCode.USER_NOT_EXIST);
        else if(!user.getPassword().equals(password))
            throw new CustomException(ResultCode.PASSWORD_NOT_CORRECT);
        return user; //如果验证成功，返回用户对象
    }
    }
//        QueryWrapper<Users100> qw = new QueryWrapper<>();
//        qw.eq("username", username);
//        Users100 user = this.getOne(qw);
//
//        if (user == null) {
//            // 用户不存在，抛出异常
//            throw new RuntimeException("用户不存在");
//        } else if (!user.getPassword().equals(password)) {
//            // 密码错误，抛出异常
//            throw new RuntimeException("密码错");
//        } else {
//            return true;
//        }

