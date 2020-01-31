package com.zero.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName(value = "admin")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminEntity {

    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Integer rid;
    private Integer status;
    private String createtime;

}
