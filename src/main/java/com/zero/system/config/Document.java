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





    /*代理上下级查询逻辑: 必须查询用户A的所有下级数据

    List<User> userList = userMapper.selectList(null); //查询用户表里所有用户数据，用来递归
    List<User> childrenList = new ArrayList<>(); //用来储存A的所有下级用户
    GetChildDataVO getChildDataVO = new GetChildDataVO(); //组装下级数据VO

    getChildren(user.getId(), userList,childrenList);  //user.getId()表示用户A的id
    packageChildren(childrenList,getChildDataVO);

    查询用户A的所有下级代理数据:
            1，先通过用户A的id把A的所有下级用户查询出来，需要使用递归


    private void getChildren(String id, List<User> userList,List<User> childrenList) {
        for (User user : userList) {
            if (user.getSpreaderId().equals(id)) {
                //当遍历的用户spreaderId等于传入id时，表面该用户为传入id的下级代理
                childrenList.add(user);
                getChildren(user.getId(),userList,childrenList);
            }
        }
    }

        2， 组装下级数据
    private void packageChildren(List<User> u, GetChildDataVO getChildDataVO) {
        if (u != null && u.size() > 0) {
            //计算下级总人数
            Integer count = 0;
            String ids = null;
            StringBuffer sb = new StringBuffer();
            for (User user : u) {
                if (user.getUserType() != NumberConstants.THREE) {
                    count++;
                    sb.append(user.getId()).append(",");
                }
            }
            ids = sb.toString().substring(0, sb.length() - 1);
            getChildDataVO.setCount(count);
            //查询下级充值总额 把用户A的所有下级的ids放在sql里 用in做一次sum求和
            getChildDataVO.setInTotal(fundMapper.selectInTotal(ids));
            //查询下级提款总额
            getChildDataVO.setOutTotal(fundMapper.selectOutTotal(ids));
            //查询下级发包总额
            getChildDataVO.setSendTotal(fundMapper.selectSend(ids));
            //下级已提现佣金
            getChildDataVO.setHisComm(fundMapper.selectHisComm(ids));
            //下级未体现佣金
            getChildDataVO.setCommission(fundMapper.selecCommission(ids));
            //下级充值优惠总额
            getChildDataVO.setPresent(fundInOutMapper.selectPresent(ids));
        }else {
            getChildDataVO.setCount(NumberConstants.ZERO);
            getChildDataVO.setInTotal(BigDecimal.ZERO);
            getChildDataVO.setOutTotal(BigDecimal.ZERO);
            getChildDataVO.setSendTotal(BigDecimal.ZERO);
            getChildDataVO.setHisComm(BigDecimal.ZERO);
            getChildDataVO.setCommission(BigDecimal.ZERO);
            getChildDataVO.setPresent(BigDecimal.ZERO);
        }
    }*/



  /*  //docker启动命令
    systemctl start docker 第一步启动docker
    docker ps -a  //看下docker容器里的mysql等程序的id
    docker start //程序id*/









}