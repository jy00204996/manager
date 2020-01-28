package com.zero.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zero.system.entity.User;
import com.zero.system.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<UserVo> queryUserList(Page<UserVo> page,UserVo userVo);

    @Update("UPDATE t_user SET deleted=#{userVo.deleted} WHERE id=#{userVo.id}")
    void recover(@Param("userVo") UserVo userVo);
}
