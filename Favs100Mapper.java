package com.lt.mapper100;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lt.VO.Heros100VO;
import com.lt.entity100.Favs100;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
public interface Favs100Mapper extends BaseMapper<Favs100> {
    // 新增：通过用户ID查询收藏的英雄完整信息
    @Select("SELECT * FROM heros100 h " +
            "WHERE h.id IN (SELECT hero_id FROM favs100 WHERE user_id = #{userId}) " +
            "ORDER BY h.id")
    List<Heros100VO> selectUserFavorites(@Param("userId") Integer userId);
}
