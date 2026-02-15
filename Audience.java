package com.lt.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "audience")
@Component
public class Audience {
    private String clientId; //
    private String base64Secret; //密钥
    private String name; //名称
    private int expiresSecond; //过期时间
}
