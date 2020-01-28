package com.zero.system.service;

import com.zero.system.util.ResultBean;
import com.zero.system.vo.CarouselVO;
import com.zero.system.vo.PageInfo;
import com.zero.system.vo.ResultPlus;

public interface CarouselService {

    ResultBean addCarousel(CarouselVO carouselVO);

    ResultPlus queryCarouselList(PageInfo pageInfo);

    ResultBean changeCarousel(Integer id, Integer status);

    ResultBean delCarousel(Integer id);

    ResultBean editCarousel(CarouselVO carouselVO);

}
