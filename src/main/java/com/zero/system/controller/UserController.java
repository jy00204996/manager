package com.zero.system.controller;

import com.zero.system.entity.BtAdmin;
import com.zero.system.entity.User;
import com.zero.system.service.UserService;
import com.zero.system.util.RedisUtil;
import com.zero.system.util.ResultBean;
import com.zero.system.vo.AdminVO;
import com.zero.system.vo.ResultPlus;
import com.zero.system.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("queryUserList")
    public ResultPlus queryUserList(UserVo userVo) {
        ResultPlus userResultBean = userService.queryUserList(userVo);
        return userResultBean;
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public ResultBean addUser(User user) {
        return userService.addUser(user);
    }

    @RequestMapping(value = "deleteUser", method = RequestMethod.POST)
    public ResultBean deleteUser(@RequestParam("id") Integer id) {
        return userService.deleteUser(id);
    }

    @RequestMapping(value = "recover", method = RequestMethod.POST)
    public ResultBean recover(UserVo userVo) {
        return userService.recover(userVo);
    }

    @RequestMapping("updateUser")
    public ResultBean updateUser(UserVo userVo) {
        return userService.updateUser(userVo);
    }


    @RequestMapping("queryAdminList")
    @ResponseBody
    public ResultPlus queryAdminList(AdminVO adminVO) {
        return userService.queryAdminList(adminVO);
    }


    @RequestMapping("btDelAdmin")
    @ResponseBody
    private ResultBean btDelAdmin(String id) {
        return userService.btDelAdmin(id);
    }

    @RequestMapping("changeAdmin")
    @ResponseBody
    public ResultBean changeAdmin(AdminVO adminVO){
        return userService.changeAdmin(adminVO);
    }

    @RequestMapping("addAdmin")
    public ResultBean addAdmin(BtAdmin btAdmin) {
        return userService.addAdmin(btAdmin);
    }

    @RequestMapping("editAdmin")
    public ResultBean editAdmin(AdminVO adminVO) {
        return userService.editAdmin(adminVO);
    }


}
