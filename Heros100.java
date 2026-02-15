package com.lt.entity100;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author litong
 * @since 2025-11-03
 */
@Data
@ApiModel(value = "Heros100对象", description = "")
public class Heros100 implements Serializable {

    private static final long serialVersionUID = 1L;

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
}
