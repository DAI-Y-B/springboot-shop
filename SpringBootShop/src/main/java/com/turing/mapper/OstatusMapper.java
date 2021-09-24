package com.turing.mapper;

import com.turing.entity.Ostatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OstatusMapper {

    /**查询当前订单的状态*/
    @Select("select * from easybuy_order_status where status_id =#{statusId}")
    Ostatus selectByIdOstatus(@Param("statusId") int statusId);

}
//status_id int primary key auto_increment /*角色编号*/,
//status_name varchar(30) /*角色名称*/
//
//statusId int primary key autoIncrement /*角色编号*/,
//statusName varchar(30) /*角色名称*/