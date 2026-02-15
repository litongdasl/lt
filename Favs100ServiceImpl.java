package com.lt.service.impl100;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.VO.Heros100VO;
import com.lt.entity100.Favs100;
import com.lt.mapper100.Favs100Mapper;
import com.lt.service100.IFavs100Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengwei
 * @since 2025-10-25
 */
@Service
public class Favs100ServiceImpl extends ServiceImpl<Favs100Mapper, Favs100> implements IFavs100Service {
    @Autowired
    private Favs100Mapper favs100Mapper;

    @Override
    public Favs100 getFavStatus100(Integer userId, Integer heroId) {
        //查询：1.mapper中写sql 2.条件构造器
        QueryWrapper<Favs100> qw=new QueryWrapper<>();
        qw.eq("user_id",userId).eq("hero_id",heroId); //构造了select * from favs where user_id=1 and product_id=4
        //这两条件查出来必定只有一条记录。unique
        return this.getOne(qw);//根据条件读取一条记录（空）
    }

    @Override
    public boolean updateFavStatus100(Integer userId, Integer heroId) {
        //原来有没有收藏
        Favs100 favs=getFavStatus100(userId, heroId);
        if(favs==null)
        {
            //insert。对象为空不能set
            favs=new Favs100(); //创建一个新对象
            favs.setUserId(userId);
            favs.setHeroId(heroId);
            return this.save(favs);
        }
        else{
            //delete
            return this.removeById(favs.getId());
        }
        //有，删除
        //没有，添加
    }
    @Override
    public List<Heros100VO> getUserFavorites(Integer userId) {
        return favs100Mapper.selectUserFavorites(userId);
    }
    @Override
    public boolean deleteFavorite100(Integer userId, Integer heroId) {
        QueryWrapper<Favs100> qw = new QueryWrapper<>();
        qw.eq("user_id", userId).eq("hero_id", heroId);
        return this.remove(qw);
    }
}
