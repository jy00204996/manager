package com.zero.system.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CarouselVO {

    private Integer id;
    private String name;
    private MultipartFile carousel; //轮播图
    private MultipartFile carouselLogo; //轮播logo

}
