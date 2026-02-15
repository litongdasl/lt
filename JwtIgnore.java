package com.lt.jwt;

import java.lang.annotation.*;

/**
 * ========================
 * JWT验证忽略注解
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/7/18 9:50
 * Version: v1.0
 * ========================
 */
@Target({ElementType.METHOD}) //修饰在方法上
@Retention(RetentionPolicy.RUNTIME) //运行时
@Documented
public @interface JwtIgnore {
}
