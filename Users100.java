package com.lt.entity100;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class Users100 {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;
    String username;
    String password;
}
