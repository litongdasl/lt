package com.lt.service100;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lt.VO.Heros100VO;
import com.lt.entity100.Heros100;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author litong
 * @since 2025-11-03
 */
public interface IHeros100Service extends IService<Heros100> {
    List<Heros100VO> selectAll100();
    Heros100VO selectById100(Integer id);
    List<Heros100VO> selectBySkin100(String skins);
    Page<Heros100VO> selectAllByPage(Integer pageNum, Integer pageSize); //第几页 一页多少条

}

