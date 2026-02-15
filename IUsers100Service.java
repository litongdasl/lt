package com.lt.service100;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.entity100.Users100;
import com.lt.handler100.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengwei
 * @since 2025-10-25
 */
public interface IUsers100Service extends IService<Users100> {
    Users100 login100(String username,String password);
}