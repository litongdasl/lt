package com.lt.controller100;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lt.VO.Heros100VO;
import com.lt.entity100.Heros100;
import com.lt.handler100.Result;
import com.lt.jwt.JwtIgnore;
import com.lt.service100.IHeros100Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author litong
 * @since 2025-11-03
 */
@RestController // 1. 表明这是REST API控制器，返回JSON数据
@RequestMapping("/heros100")
@CrossOrigin //跨域
public class Heros100Controller {
    @Autowired // 4. 自动注入服务层
    private IHeros100Service heros100Service;
    @GetMapping("/Heros100VO") // 获取所有英雄（不分页）
    public Result listVO() {
        return Result.SUCCESS(heros100Service.selectAll100());
    }
    @GetMapping("/selectById100")//根据ID查询单个英雄
    public Result selectById100(Integer id) {
        return Result.SUCCESS(heros100Service.selectById100(id));
    }


    @PostMapping("/heros100")// 添加英雄
    public Result insert(@RequestBody Heros100 heros100) //前端 写json，转成products对象。@RequestBody
    {
        return heros100Service.save(heros100)?Result.SUCCESS():Result.FAIL();
    }
    @PutMapping("/heros100")//修改英雄
    public Result update(@RequestBody Heros100 heros100) //前端 写json，转成products对象。@RequestBody
    {
        return heros100Service.updateById(heros100)?Result.SUCCESS():Result.FAIL();
    }
    @DeleteMapping("/heros100/{id}")//删除英雄
    public Result delete(@PathVariable("id") Integer id)
    {
        return heros100Service.removeById(id)?Result.SUCCESS():Result.FAIL("数据不存在"); //mybatisplus提供的按主键查询的方法
    }
    // 按皮肤名称模糊查询（含职业名称）
    @GetMapping("/selectBySkin")//按皮肤名称模糊查询
    public Result selectBySkin(@RequestParam String skins) {
        // 调用服务层方法，传入皮肤名称关键字
        return Result.SUCCESS(heros100Service.selectBySkin100(skins));
    }
    @JwtIgnore //    不提供 token 直接访问。
    @GetMapping("/selectAllByPage") //分页获取英雄列表
    public Result listVOPage(Integer pageNum, Integer pageSize)
    {
        pageNum=pageNum==null?1:pageNum;
        pageSize=pageSize==null?10:pageSize;
        return Result.SUCCESS(heros100Service.selectAllByPage(pageNum,pageSize)); //mybatisplus提供的查全表方法list
    }
    // 新增：获取所有英雄类型的方法
    @GetMapping("/heroTypes")
    public Result getHeroTypes() {
        List<Heros100VO> allHeros = heros100Service.selectAll100();
        // 使用Stream去重获取所有英雄类型
        return Result.SUCCESS(allHeros.stream()
                .map(Heros100VO::getHeroTypeName)// 提取类型名称
                .distinct() // 去重
                .collect(Collectors.toList()));// 转为列表
    }
}
//@RestController
//@RequestMapping("/heros100")
//@CrossOrigin //跨域
//public class Heros100Controller {
//    @Autowired
//    private IHeros100Service heros100Service;
//    @GetMapping("/Heros100VO") //有品牌名称，没分页
//    public List<Heros100VO> listVO() {
//        return heros100Service.selectAll100();
//    }
//    @GetMapping("/selectById100")
//    public Heros100VO selectById100(Integer id) {
//        return heros100Service.selectById100(id);
//    }
//
//
//    @PostMapping("/heros100")
//    public boolean insert(@RequestBody Heros100 heros100) //前端 写json，转成products对象。@RequestBody
//    {
//        return heros100Service.save(heros100);
//    }
//    @PutMapping("/heros100")
//    public boolean update(@RequestBody Heros100 heros100) //前端 写json，转成products对象。@RequestBody
//    {
//        return heros100Service.updateById(heros100);
//    }
//    @DeleteMapping("/heros100/{id}")
//    public boolean delete(@PathVariable("id") Integer id)
//    {
//        return heros100Service.removeById(id); //mybatisplus提供的按主键查询的方法
//    }
//    // 按皮肤名称模糊查询（含职业名称）
//    @GetMapping("/selectBySkin")
//    public List<Heros100VO> selectBySkin(@RequestParam String skins) {
//        // 调用服务层方法，传入皮肤名称关键字
//        return heros100Service.selectBySkin100(skins);
//    }
//    @GetMapping("/selectAllByPage") //有品牌名称，有分页
//    public Page<Heros100VO> listVOPage(Integer pageNum, Integer pageSize)
//    {
//        pageNum=pageNum==null?1:pageNum;
//        pageSize=pageSize==null?10:pageSize;
//        return heros100Service.selectAllByPage(pageNum,pageSize); //mybatisplus提供的查全表方法list
//    }
//    // 新增：获取所有英雄类型的方法
//    @GetMapping("/heroTypes")
//    public List<String> getHeroTypes() {
//        List<Heros100VO> allHeros = heros100Service.selectAll100();
//        // 使用Stream去重获取所有英雄类型
//        return allHeros.stream()
//                .map(Heros100VO::getHeroTypeName)
//                .distinct()
//                .collect(Collectors.toList());
//    }
//}