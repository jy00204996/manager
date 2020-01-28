package com.zero.system.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.system.config.SystemConfig;
import com.zero.system.entity.Carousel;
import com.zero.system.mapper.CarouselMapper;
import com.zero.system.service.CarouselService;
import com.zero.system.util.FileUtils;
import com.zero.system.util.ResultBean;
import com.zero.system.vo.CarouselVO;
import com.zero.system.vo.PageInfo;
import com.zero.system.vo.ResultPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Value("${ACTPATH}")
    private String ACTPATH;
    @Value("${URLACTPATH}")
    private String URLACTPATH;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultBean addCarousel(CarouselVO carouselVO) {
        String actPath = ACTPATH+"/carousel/";  //物理路径
        String urlPath = URLACTPATH+"/carousel/";  //访问路径
        Carousel cl = new Carousel();
        MultipartFile carousel = carouselVO.getCarousel();
        if (carousel != null) {
            List<String> fileNames = FileUtils.saveFile(actPath, carousel);
            cl.setCarousel(urlPath+fileNames.get(0));
        }
        MultipartFile carouselLogo = carouselVO.getCarouselLogo();
        if (carouselLogo != null) {
            List<String> fileNames = FileUtils.saveFile(actPath, carouselLogo);
            cl.setCarouselLogo(urlPath+fileNames.get(0));
        }
        cl.setName(carouselVO.getName());
        cl.setStatus(SystemConfig.STATUS_ON);
        cl.setDeleted(SystemConfig.DELETE_ON);
        cl.setCreateTime(new Date());
        cl.setUpdateTime(new Date());
        carouselMapper.insert(cl);
        return new ResultBean(SystemConfig.SUCCESS_CODE, "新增成功", SystemConfig.SUCCESS_MSG);
    }

    @Override
    public ResultBean editCarousel(CarouselVO carouselVO) {
        String actPath = ACTPATH+"/carousel/";  //物理路径
        String urlPath = URLACTPATH+"/carousel/";  //访问路径
        Carousel cl = new Carousel();
        MultipartFile carousel = carouselVO.getCarousel();
        if (carousel != null) {
            List<String> fileNames = FileUtils.saveFile(actPath, carousel);
            cl.setCarousel(urlPath+fileNames.get(0));
        }
        MultipartFile carouselLogo = carouselVO.getCarouselLogo();
        if (carouselLogo != null) {
            List<String> fileNames = FileUtils.saveFile(actPath, carouselLogo);
            cl.setCarouselLogo(urlPath+fileNames.get(0));
        }
        cl.setId(carouselVO.getId());
        cl.setName(carouselVO.getName());
        cl.setStatus(SystemConfig.STATUS_ON);
        cl.setDeleted(SystemConfig.DELETE_ON);
        cl.setCreateTime(new Date());
        cl.setUpdateTime(new Date());
        carouselMapper.updateById(cl);
        return new ResultBean(SystemConfig.SUCCESS_CODE, "修改成功", SystemConfig.SUCCESS_MSG);
    }



    @Override
    public ResultPlus queryCarouselList(PageInfo pageInfo) {
        ResultPlus rlt = new ResultPlus();
        QueryWrapper<Carousel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", SystemConfig.DELETE_ON);
        queryWrapper.orderByDesc("create_time");
        IPage<Carousel> sele = carouselMapper.selectPage(new Page<Carousel>(pageInfo.getPage(),pageInfo.getPageSize()), queryWrapper);
        rlt.setRows(sele.getRecords());
        rlt.setTotal(sele.getTotal());
        return rlt;
    }

    @Override
    public ResultBean changeCarousel(Integer id, Integer status) {
        Integer s = null;
        if (status == 1) {
            s=0;
        }else {
            s=1;
        }
        carouselMapper.changeCarousel(id,s);
        return new ResultBean(SystemConfig.SUCCESS_CODE, "修改成功", SystemConfig.SUCCESS_MSG);
    }

    @Override
    public ResultBean delCarousel(Integer id) {
        carouselMapper.delCarousel(id);
        return new ResultBean(SystemConfig.SUCCESS_CODE, "删除成功", SystemConfig.SUCCESS_MSG);
    }



}
