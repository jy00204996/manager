package com.zero.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "t_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    @TableId(type = IdType.INPUT)
    private String id;
    private String name;
    private String age;
    private String gender;
    private String wallet;
    private String password;
    private Integer deleted;
    private Date createTime;
    private Date updateTime;
    private Integer roleId;
    private String nickName;
    private Integer status;

}
