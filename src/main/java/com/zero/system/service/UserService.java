package com.zero.system.service;

import com.zero.system.entity.BtAdmin;
import com.zero.system.entity.User;
import com.zero.system.util.ResultBean;
import com.zero.system.vo.AdminVO;
import com.zero.system.vo.ResultPlus;
import com.zero.system.vo.UserVo;

public interface UserService {

    ResultPlus queryUserList(UserVo userVo);

    ResultBean addUser(User user);

    ResultBean deleteUser(Integer id);

    ResultBean recover(UserVo userVo);

    ResultBean updateUser(UserVo userVo);

    ResultPlus queryAdminList(AdminVO adminVO);

    ResultBean btDelAdmin(String id);

    ResultBean changeAdmin(AdminVO adminVO);

    ResultBean addAdmin(BtAdmin btAdmin);

    ResultBean editAdmin(AdminVO adminVO);

    User queryUser(String id);

}
