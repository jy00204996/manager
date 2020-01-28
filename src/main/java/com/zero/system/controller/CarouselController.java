package com.zero.system.controller;


import com.zero.system.service.CarouselService;
import com.zero.system.util.ResultBean;
import com.zero.system.vo.CarouselVO;
import com.zero.system.vo.PageInfo;
import com.zero.system.vo.ResultPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carousel")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    @RequestMapping("addCarousel")
    public ResultBean addCarousel(CarouselVO carouselVO) {
        return carouselService.addCarousel(carouselVO);
    }

    @RequestMapping("editCarousel")
    public ResultBean editCarousel(CarouselVO carouselVO) {
        return carouselService.editCarousel(carouselVO);
    }

    @RequestMapping("queryCarouselList")
    public ResultPlus queryCarouselList(PageInfo pageInfo) {
        return carouselService.queryCarouselList(pageInfo);
    }

    @PostMapping("changeCarousel")
    public ResultBean changeCarousel(Integer id, Integer status) {
        return carouselService.changeCarousel(id, status);
    }

    @RequestMapping("delCarousel")
    public ResultBean delCarousel(Integer id) {
        return carouselService.delCarousel(id);
    }
}
