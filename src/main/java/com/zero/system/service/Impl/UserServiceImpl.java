package com.zero.system.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.system.config.SystemConfig;
import com.zero.system.entity.BtAdmin;
import com.zero.system.entity.User;
import com.zero.system.mapper.BtAdminMapper;
import com.zero.system.mapper.UserMapper;
import com.zero.system.service.UserService;
import com.zero.system.util.ResultBean;
import com.zero.system.vo.AdminVO;
import com.zero.system.vo.ResultPlus;
import com.zero.system.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BtAdminMapper btAdminMapper;

    @Override
    public ResultPlus queryUserList(UserVo userVo) {
        Page<UserVo> page = new Page(userVo.getPage(),userVo.getPageSize());
        List<UserVo> users = userMapper.queryUserList(page,userVo);
        return new ResultPlus(page.getTotal(),users);

    }

    @Override
    public User queryUser(String id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public ResultBean addUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", user.getName());
        User exist = userMapper.selectOne(queryWrapper);
        if (exist != null) {
            return new ResultBean(SystemConfig.ERROR_CODE,"添加失败，该用户已存在！",SystemConfig.ERROR_MSG);
        }
        user.setCreateTime(new Date());
        user.setDeleted(SystemConfig.DELETE_ON);
        user.setStatus(SystemConfig.STATUS_ON);
        user.setRoleId(SystemConfig.ROLE_ID_0);
        userMapper.insert(user);
        return new ResultBean(SystemConfig.SUCCESS_CODE,"新增成功",SystemConfig.SUCCESS_MSG);
    }

    @Override
    public ResultBean deleteUser(Integer id) {
        User user = userMapper.selectById(id);
        if (user != null) {
            user.setDeleted(SystemConfig.DELETE_OFF);
            userMapper.updateById(user);
            if (!(userMapper.updateById(user) > 0)) {
                return new ResultBean(SystemConfig.ERROR_CODE,"删除失败",SystemConfig.ERROR_MSG);
            }
        }
        return new ResultBean(SystemConfig.SUCCESS_CODE,"删除成功",SystemConfig.SUCCESS_MSG);
    }

    @Override
    public ResultBean recover(UserVo userVo) {
        if (userVo.getDeleted().equals("1")) {
            userVo.setDeleted("0");
        }
        userMapper.recover(userVo);
        return new ResultBean(SystemConfig.SUCCESS_CODE,"恢复成功",SystemConfig.SUCCESS_MSG);
    }

    @Override
    public ResultBean updateUser(UserVo userVo) {
        User user = userMapper.selectById(userVo.getId());
        if (userVo.getAge() != null) {
            user.setAge(userVo.getAge());
        }
        if (userVo.getGender() != null) {
            user.setGender(userVo.getGender());
        }
        if (userVo.getPassword() != null) {
            user.setPassword(userVo.getPassword());
        }
        if (userVo.getWallet() != null) {
            user.setWallet(userVo.getWallet());
        }
        if (userVo.getNickName() != null) {
            user.setNickName(userVo.getNickName());
        }
        user.setUpdateTime(new Date());
        if (!(userMapper.updateById(user) > 0)) {
            return new ResultBean(SystemConfig.ERROR_CODE,null,SystemConfig.ERROR_MSG);
        }
        return new ResultBean(SystemConfig.SUCCESS_CODE,user,SystemConfig.SUCCESS_MSG);
    }

    @Override
    public ResultPlus queryAdminList(AdminVO adminVO) {
        QueryWrapper<BtAdmin> queryWrapper = new QueryWrapper<>();
        Page<BtAdmin> page = new Page(adminVO.getPage(),adminVO.getPageSize());
        if (StringUtils.isNotEmpty(adminVO.getAdminCode())) {
            queryWrapper.eq("admin_code", adminVO.getAdminCode());
        }
        queryWrapper.ne("deleted", 1);
        IPage<BtAdmin> btAdminIPage = btAdminMapper.selectPage(page, queryWrapper);
        return new ResultPlus(btAdminIPage.getTotal(),btAdminIPage.getRecords());
    }

    @Override
    public ResultBean btDelAdmin(String id) {
        BtAdmin btAdmin = btAdminMapper.selectById(id);
        btAdmin.setDeleted(SystemConfig.DELETE_OFF);
        btAdminMapper.updateById(btAdmin);
        return new ResultBean("删除成功");
    }

    @Override
    public ResultBean changeAdmin(AdminVO adminVO) {
        if (adminVO.getStatus() == 1) {
            adminVO.setStatus(0);
        }else{
            adminVO.setStatus(1);
        }
        Integer integer = btAdminMapper.changeAdmin(adminVO.getId(),adminVO.getStatus());
        return new ResultBean("修改成功");
    }

    @Override
    public ResultBean addAdmin(BtAdmin btAdmin) {
        btAdmin.setCreateTime(new Date());
        btAdmin.setDeleted(0);
        btAdmin.setStatus(1);
        btAdminMapper.insert(btAdmin);
        return new ResultBean("修改成功");
    }

    @Override
    public ResultBean editAdmin(AdminVO adminVO) {
        BtAdmin admin = btAdminMapper.selectById(adminVO.getId());
        if (adminVO.getNickName() != null) {
            admin.setNickName(adminVO.getNickName());
        }
        if (adminVO.getPassword() != null) {
            admin.setPassword(adminVO.getPassword());
        }
        if (adminVO.getMark() != null) {
            admin.setMark(adminVO.getMark());
        }
        btAdminMapper.updateById(admin);
        return new ResultBean("修改成功");
    }


}
