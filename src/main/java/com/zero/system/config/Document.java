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


 /*   //线程同步锁
    private static boolean LOCK = true;

    //为true才能执行该方法，保证线程安全
    if(LOCK){
        getCode(x,y,z)
    }

    //线程同步锁
    @Transactional(propagation = Propagation.REQUIRED)
    public synchronized Integer getCode(x,y,z) {
        Integer code = 0;
        LOCK = false;
        //要执行的方法，审批转账业务流
        LOCK = true;
        return code;
    }*/

    /*//工具类里调用mapper或者redis
    @Component
    public class OperationUtils {

        @Resource
        private AdminMapper amp;
        @Resource
        private UserMapper ump;
        @Resource
        private FundMapper fmp;

        private static AdminMapper adminMapper;
        private static UserMapper userMapper;
        private static FundMapper fundMapper;

        @PostConstruct
        public void init() {
            adminMapper = amp;
            userMapper = ump;
            fundMapper = fmp;
        }
    }*/


}