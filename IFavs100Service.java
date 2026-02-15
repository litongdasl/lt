package com.lt.service100;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lt.VO.Heros100VO;
import com.lt.entity100.Favs100;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengwei
 * @since 2025-10-25
 */
public interface IFavs100Service extends IService<Favs100> {
    //用户有没有收藏？？商品
    Favs100 getFavStatus100(Integer userId,Integer heroId); //查询，两种方法：1.在mapper中写sql 2.条件构造器
    boolean updateFavStatus100(Integer userId,Integer heroId);
    // 新增：获取用户收藏的英雄完整信息
    List<Heros100VO> getUserFavorites(Integer userId);
    boolean deleteFavorite100(Integer userId, Integer heroId);
}