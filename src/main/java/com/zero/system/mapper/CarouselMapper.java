package com.zero.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero.system.entity.Carousel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CarouselMapper extends BaseMapper<Carousel> {
    @Update("UPDATE t_carousel SET status=#{status} WHERE id=#{id}")
    void changeCarousel(Integer id, Integer status);

    @Update("UPDATE t_carousel SET deleted=1 WHERE id=#{id}")
    void delCarousel(Integer id);
}
