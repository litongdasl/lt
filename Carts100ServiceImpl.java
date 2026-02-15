package com.lt.service.impl100;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lt.VO.Carts100VO;
import com.lt.entity100.Carts100;
import com.lt.handler100.CustomException;
import com.lt.handler100.ResultCode;
import com.lt.mapper100.Carts100Mapper;
import com.lt.service100.ICarts100Service;
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
public class Carts100ServiceImpl extends ServiceImpl<Carts100Mapper, Carts100> implements ICarts100Service {
    @Autowired
    private Carts100Mapper cartsMapper;
    @Override
    public boolean addCart100(Integer userId, Integer heroId, Integer num) {
        //用户购物车中有没有该商品，查询
        //查询：1.sql 2.QueryWrapper条件构造器
        QueryWrapper<Carts100> qw=new QueryWrapper<>();
        qw.eq("user_id",userId).eq("hero_id",heroId); //column：数据表的字段 property：实体的属性
        //这两个条件查询出来的结果只有1条
        Carts100 cart=this.getOne(qw); //查询把结果放在cart
        if(cart==null)
        {
            //insert
            cart=new Carts100(); //先创建一个实例/对象
            cart.setUserId(userId); //null的对象不可能可以set
            cart.setHeroId(heroId);
            cart.setNum(num);
            return this.save(cart);
        }
        else{
            //update：数量，是在原先的基础上相加
            cart.setNum(cart.getNum()+num);
            return this.updateById(cart);
        }
        //1.有。update
        //2.没有。insert
    }

    @Override
    public Integer getCartNum100(Integer userId) {
        return cartsMapper.getCartNum100(userId);
    }

    @Override
    public List<Carts100VO> getCartByUserId100(Integer userId) {
        return cartsMapper.getCartByUserId100(userId);
    }

//    @Override
//    public Double getCartTotalAmount(Integer userId) {
//        return cartsMapper.getCartTotalAmount(userId);
//    }
    @Override
    public boolean deleteCart100(Integer userId, Integer heroId) {
        QueryWrapper<Carts100> qw=new QueryWrapper<>();
        qw.eq("user_id",userId).eq("hero_id",heroId);
        return this.remove(qw); //有影响行数为true，没有就是false
    }

    @Override
    public boolean incrNum100(Integer userId, Integer heroId) {
        //sql
        //新增：库存。
        UpdateWrapper<Carts100> uw=new UpdateWrapper<>();//更新carts
        uw.eq("user_id",userId).eq("hero_id",heroId); //where user_id=1 and product_id=3
        uw.setSql("num=num+1"); //update carts set num=num+1 where user_id=1 and product_id=2
        return this.update(uw); //更新操作
    }

    @Override
    public boolean decrNum100(Integer userId, Integer heroId) {
        //数量变成0就是删除
        //>1，减
        QueryWrapper<Carts100> qw=new QueryWrapper<>();//更新carts
        qw.eq("user_id",userId).eq("hero_id",heroId);
        Carts100 carts=this.getOne(qw); //查询出来
        if(carts==null)
            throw new CustomException(ResultCode.RESULT_DATA_NONE);
        if(carts.getNum()>1) {
            //=1，删除
            UpdateWrapper<Carts100> uw = new UpdateWrapper<>();//更新carts
            uw.eq("user_id", userId).eq("hero_id", heroId); //where user_id=1 and product_id=3
            uw.setSql("num=num-1"); //update carts set num=num+1 where user_id=1 and product_id=2
            return this.update(uw); //更新操作
        }
        else{ //=1，删除
            return this.removeById(carts.getId());
        }
    }
    @Override
    public boolean clearCart100(Integer userId) {
        QueryWrapper<Carts100> qw = new QueryWrapper<>();
        qw.eq("user_id", userId);
        return this.remove(qw);
    }

}