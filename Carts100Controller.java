package com.lt.controller100;

import com.lt.handler100.Result;
import com.lt.jwt.Audience;
import com.lt.jwt.JwtTokenUtil;
import com.lt.service100.ICarts100Service;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts100")
@CrossOrigin
public class Carts100Controller {
    //添加购物车：数据改动，post。数据查询，get。
    //restful api：post/get/put/delete。
    @Autowired
    private Audience audience;
    @Autowired
    private ICarts100Service cartsService;
    @PostMapping("/addCart100")
    Result addCart(HttpServletRequest request, Integer heroId, Integer num)
    {
        //用户号不要作为参数。
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        final String token = authHeader.substring(7); //取下标为7开始的内容
        Integer userId= JwtTokenUtil.getUserId(token,audience.getBase64Secret());
        return Result.SUCCESS(cartsService.addCart100(userId,heroId,num));
    }
    @GetMapping("/getCartNum100") //读取商品总数量
    Result getCartNum100(HttpServletRequest request)
    {
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        final String token = authHeader.substring(7); //取下标为7开始的内容
        Integer userId= JwtTokenUtil.getUserId(token,audience.getBase64Secret());
        return Result.SUCCESS(cartsService.getCartNum100(userId));
    }
    @GetMapping("/getCartByUserId100")
    Result getCartByUserId100(HttpServletRequest request)
    {
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        final String token = authHeader.substring(7); //取下标为7开始的内容
        Integer userId= JwtTokenUtil.getUserId(token,audience.getBase64Secret());
        return Result.SUCCESS(cartsService.getCartByUserId100(userId));
    }
//    @GetMapping("/getCartTotalAmountByUserId100")
//    Result getCartTotalAmountByUserId(HttpServletRequest request)
//    {
//        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
//        final String token = authHeader.substring(7); //取下标为7开始的内容
//        Integer userId= JwtTokenUtil.getUserId(token,audience.getBase64Secret());
//        return Result.SUCCESS(cartsService.getCartTotalAmount100(userId));
//    }
    //数据改动：post
    @PostMapping("/deleteCart100")
    Result deleteCart(HttpServletRequest request,Integer heroId) //控制用户只能删自己的购物车
    {
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        final String token = authHeader.substring(7); //取下标为7开始的内容
        Integer userId= JwtTokenUtil.getUserId(token,audience.getBase64Secret());
        return Result.SUCCESS(cartsService.deleteCart100(userId,heroId));
    }
    @PostMapping("/incrNum100")
    Result incrNum100(HttpServletRequest request,Integer heroId) //控制用户只能删自己的购物车
    {
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        final String token = authHeader.substring(7); //取下标为7开始的内容
        Integer userId= JwtTokenUtil.getUserId(token,audience.getBase64Secret());
        return Result.SUCCESS(cartsService.incrNum100(userId,heroId));
    }
    @PostMapping("/decrNum100")
    Result decrNum100(HttpServletRequest request,Integer heroId) //控制用户只能删自己的购物车
    {
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        final String token = authHeader.substring(7); //取下标为7开始的内容
        Integer userId= JwtTokenUtil.getUserId(token,audience.getBase64Secret());
        return Result.SUCCESS(cartsService.decrNum100(userId,heroId));
    }
    @PostMapping("/cclearCart100")
    Result clearCart100(HttpServletRequest request)
    {
        final String authHeader = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        final String token = authHeader.substring(7);
        Integer userId = JwtTokenUtil.getUserId(token, audience.getBase64Secret());
        return Result.SUCCESS(cartsService.clearCart100(userId));
    }
}
