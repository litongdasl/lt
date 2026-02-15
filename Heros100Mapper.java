package com.lt.mapper100;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lt.VO.Heros100VO;
import com.lt.entity100.Heros100;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author litong
 * @since 2025-11-03
 */
@Mapper
public interface Heros100Mapper extends BaseMapper<Heros100> {
    //查全表/有英雄职业名称名称
    @Select("select * from heros100 left outer join hero_type100 on hero_type100.id=heros100.hero_type") //复杂sql不适合写在java文件中。
    List<Heros100VO> selectAll100();
    // 按主键查询（含职业名称）
    @Select("select * from heros100 left outer join hero_type100 on hero_type100.id=heros100.hero_type where heros100.id = #{id}")
    Heros100VO selectById100(@Param("id") Integer id);
    // 按skins模糊查询（含职业名称）
    @Select("select * from heros100 left outer join hero_type100 on hero_type100.id=heros100.hero_type where heros100.skins like concat('%', #{skins}, '%')")
    List<Heros100VO> selectBySkin100(@Param("skins") String skins);
    @Select("select * from heros100 left outer join hero_type100 on hero_type100.id=heros100.hero_type") //复杂sql不适合写在java文件中。
        //传参数，返回的是某一页的数据
    Page<Heros100VO> selectAllByPage(Page<Heros100VO> page); //page中存储了：第几页，一页多少条
}


