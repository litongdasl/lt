package com.lt.VO;

import lombok.Data;

//查询用的，不需要加其他注解
@Data //加上getter/setter
public class Heros100VO {
    private Integer id;

    private String heroName;

    private String title;

    private Integer newType;

    private Integer heroType;

    private String skins;

    private Integer survivalPercent;

    private Integer attackPercent;

    private Integer skillPercent;

    private Integer difficultyPercent;
    private String heroTypeName;
}
