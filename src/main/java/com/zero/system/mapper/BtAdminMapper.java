package com.zero.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zero.system.entity.BtAdmin;
import org.apache.ibatis.annotations.Update;

public interface BtAdminMapper extends BaseMapper<BtAdmin> {

    @Update("update t_admin set status=#{status} where id=#{id}")
    Integer changeAdmin(String id, Integer status);

}
