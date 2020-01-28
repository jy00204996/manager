package com.zero.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zero.system.vo.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName(value = "t_admin")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BtAdmin{

    @TableId(type = IdType.INPUT) //不让mybatis-plus生成long类型的id, 我们需要的是让id自增
    private String id;
    private String adminCode;
    private String password;
    private Date createTime;
    private String nickName;
    private Integer status;
    private Integer deleted;
    private String mark;

}
