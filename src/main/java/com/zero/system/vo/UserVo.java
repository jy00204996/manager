package com.zero.system.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVo extends PageInfo implements Serializable{

    private Integer id;
    private String name;
    private String age;
    private String gender;
    private String wallet;
    private String password;
    private String nickName;
    private String startTime;
    private String endTime;
    private String deleted;
    private String status;

}
