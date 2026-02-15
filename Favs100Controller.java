package com.lt.controller100;

import com.lt.handler100.Result;
import com.lt.jwt.Audience;
import com.lt.jwt.JwtTokenUtil;
import com.lt.service100.IFavs100Service;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favs100")
@CrossOrigin
public class Favs100Controller {
    @Autowired
    private IFavs100Service favsService;
    @Autowired
    private Audience audience; //不用new，都是注入
    @GetMapping("/getFavStatus100") //没有开放，需要token
    Result getFavStatus100(HttpServletRequest request, Integer heroId) //用户号，商品号。不安全：用户号不要当参数，不安全，能查任何用户的收藏情况
    {
        //用户号不要当参数，从token获取。
        //给前端返回true/false
        //获取token，从token中取出用户号。
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        final String token = authHeader.substring(7); //取下标为7开始的内容
        Integer userId= JwtTokenUtil.getUserId(token,audience.getBase64Secret());
        return Result.SUCCESS(this.favsService.getFavStatus100(userId,heroId)!=null?true:false);
    }
    //功能涉及数据改动，post
    @PostMapping("/updateFavStatus100")
    Result updateFavStatus100(HttpServletRequest request, Integer heroId)
    {
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        final String token = authHeader.substring(7); //取下标为7开始的内容
        Integer userId= JwtTokenUtil.getUserId(token,audience.getBase64Secret());
        return Result.SUCCESS(this.favsService.updateFavStatus100(userId,heroId));
    }
    // 新增：获取用户收藏的所有英雄
    @GetMapping("/getUserFavorites")
    Result getUserFavorites(HttpServletRequest request)
    {
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        final String token = authHeader.substring(7);
        Integer userId = JwtTokenUtil.getUserId(token, audience.getBase64Secret());
        return Result.SUCCESS(this.favsService.getUserFavorites(userId));
    }
    @DeleteMapping("/deleteFavorite100")
    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.OPTIONS})
    Result deleteFavorite100(HttpServletRequest request, Integer heroId) {
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        final String token = authHeader.substring(7);
        Integer userId = JwtTokenUtil.getUserId(token, audience.getBase64Secret());
        return Result.SUCCESS(this.favsService.deleteFavorite100(userId, heroId));
    }
}
