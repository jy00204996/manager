package com.zero.system.config;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class Document {



 /* mybatis-plus多表联查,在sql.xml中使用分页page
    impl:
    Page<ActivityVO> page = new Page(activity.getPage(),activity.getPageSize());
    List<ActivityVO> list = activityMapper.selectActivity(page,activity);
    mapper:
    List<ActivityVO> selectActivity(Page<ActivityVO> page,ActivityVO activity);
    10.14.23.236 root/dlgj@123
    mybatis-plus单表分页
    impl:
    Page<Notice> page = new Page(pageInfo.getPage(),pageInfo.getPageSize());
    QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
    IPage<Notice> selectPage = noticeMapper.selectPage(page, wrapper);
        return new ResultInfoVO(selectPage.getTotal(),selectPage.getRecords());
    */

/*    上事物:只需要在该方法上打上这个注解就行
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)*/


}
