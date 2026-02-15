package com.lt.service100;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.VO.Carts100VO;
import com.lt.entity100.Carts100;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengwei
 * @since 2025-10-25
 */
public interface ICarts100Service extends IService<Carts100> {
    boolean addCart100(Integer userId,Integer heroId,Integer num);
    Integer getCartNum100(Integer userId);
    //查询某用户的购物车所有的商品
    List<Carts100VO> getCartByUserId100(Integer userId);

//    Double getCartTotalAmount(Integer userId);
    boolean deleteCart100(Integer userId,Integer hero);
    boolean incrNum100(Integer userId,Integer hero);
    boolean decrNum100(Integer userId,Integer hero);
    // 在 ICarts100Service 接口中添加
    boolean clearCart100(Integer userId);
}