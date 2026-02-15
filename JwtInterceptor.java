package com.lt.jwt;

import com.lt.handler100.CustomException;
import com.lt.handler100.ResultCode;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
/**
 * ========================
 * token验证拦截器
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/7/18 9:46
 * Version: v1.0
 * ========================
 */
@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private Audience audience;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //OPTIONS是一种请求类型，代码中axios发get时，实际发2个请求，OPTIONS（测试，允许 ）/GET
        //请求类型：GET/POST/PUT/DELETE
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true; //如果OPTIONS请求直接通过。
        }

        //请求的方法的注解
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class); //获取方法上的注解(Annotation)是否是JwtIgnore类型
            if (jwtIgnore != null) { //非空，方法上有JwtIgnore注解，直接允许通过
                return true;
            }
        }
        //需要验证
        // 获取请求头信息authorization信息
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);//ctrl+鼠标左键
//        log.info("## authHeader= {}", authHeader);

        //如果是空：没给token不能访问，如果前缀不是Bearer ，也不能访问
        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            log.info("### 用户未登录，请先登录 ###");
            throw new CustomException(ResultCode.USER_NOT_LOGGED_IN);
        }

        // 获取token
        final String token = authHeader.substring(7); //去掉前缀Bearer，只留下token

        //audience为空，从配置文件中读取内容，装载到audience对象
        if(audience == null){
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            audience = (Audience) factory.getBean("audience");
        }

        // 验证token是否有效--无效已做异常抛出，由全局异常处理后返回对应信息
        JwtTokenUtil.parseJWT(token, audience.getBase64Secret()); //解析一下token是否有效，token无效抛异常，token过期也抛异常
        String username=JwtTokenUtil.getUsername(token,audience.getBase64Secret()); //从token中读取用户名

        StringBuilder sb = new StringBuilder(1000);
        HandlerMethod h = (HandlerMethod) handler;
        sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
        sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
        sb.append("Params    : ").append(getParamString(request.getParameterMap())).append("\n");
        sb.append("URI       : ").append(request.getRequestURI()).append("\n");
        log.info("### 用户"+username+'\n'+sb.toString());
        return true;
    }
    private String getParamString(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,String[]> e:map.entrySet()){
            sb.append(e.getKey()).append("=");
            String[] value = e.getValue();
            if(value != null && value.length == 1){
                sb.append(value[0]).append("\t");
            }else{
                sb.append(Arrays.toString(value)).append("\t");
            }
        }
        return sb.toString();
    }

}
