package com.zero.system.config;

import lombok.Data;

@Data
public final class SystemConfig {

    public final static int SUCCESS_CODE =1;
    public final static int ERROR_CODE =0;
    /**
     * 软删除  0代表未删除，1代表已删除
     */
    public final static int DELETE_ON =0;
    /**
     * 软删除  0代表未删除，1代表已删除
     */
    public final static int DELETE_OFF =1;
    /**
     * 用户状态  1代表开启，0代表关闭
     */
    public final static int STATUS_ON =1;
    /**
     * 用户状态  1代表开启，0代表关闭
     */
    public final static int STATUS_OFF =0;

    public final static String SUCCESS_MSG = "成功";
    public final static String ERROR_MSG = "失败";

    /**
     * 用户权限，0代表最高权限，1代表普通用户
     */
    public final static int ROLE_ID_0 = 0;
    /**
     * 用户权限，0代表最高权限，1代表普通用户
     */
    public final static int ROLE_ID_1 = 1;

}
