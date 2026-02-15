package com.lt.mapper100;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.VO.Carts100VO;
import com.lt.entity100.Carts100;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zengwei
 * @since 2025-10-25
 */
@Mapper
public interface Carts100Mapper extends BaseMapper<Carts100> {
    @Select("select sum(num) from carts100 where user_id=#{userId}")
    Integer getCartNum100(Integer userId);
//    @Select("select sum(num*price) from carts100,heros100 where carts100.hero_id=hero100.id and  user_id=1")
//    Double getCartTotalAmount100(Integer userId);
    @Select("select carts100.*,title,hero_name from carts100,heros100 where heros100.id=carts100.hero_id and user_id=#{userId}")
    List<Carts100VO> getCartByUserId100(Integer userId);
}

