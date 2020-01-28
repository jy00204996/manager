package com.zero.system.vo;

import lombok.Data;

@Data
public class AdminVO extends PageInfo{

    private String id;
    private String adminCode;
    private String password;
    private String nickName;
    private Integer status;
    private String mark;

}
