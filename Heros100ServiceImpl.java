package com.lt.service.impl100;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lt.VO.Heros100VO;
import com.lt.entity100.Heros100;
import com.lt.mapper100.Heros100Mapper;
import com.lt.service100.IHeros100Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author litong
 * @since 2025-11-03
 */
@Service
public class Heros100ServiceImpl extends ServiceImpl<Heros100Mapper, Heros100> implements IHeros100Service {
   @Autowired
    private Heros100Mapper heros100Mapper;
   @Override
    public List<Heros100VO> selectAll100(){
       return heros100Mapper.selectAll100();
   }
    @Override
    public Heros100VO selectById100(Integer id) {
        return heros100Mapper.selectById100(id);
    }
    @Override
    public  List<Heros100VO> selectBySkin100(String skins){return heros100Mapper.selectBySkin100(skins);}
    @Override
    public Page<Heros100VO> selectAllByPage(Integer pageNum, Integer pageSize) {
        Page<Heros100VO> page = new Page<>(pageNum, pageSize); //将第几页，一页多少条创建了一个Page对象
        return heros100Mapper.selectAllByPage(page); //参数传过去，返回某一页数据
    }
}
