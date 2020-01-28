package com.zero.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@Data
@TableName(value = "t_carousel")
public class Carousel {

    @TableId(type = IdType.INPUT)
    private Integer id;
    private String name;
    private String carousel;
    private String carouselLogo;
    private Integer status;
    private Integer deleted;
    private Date createTime;
    private Date updateTime;

}
