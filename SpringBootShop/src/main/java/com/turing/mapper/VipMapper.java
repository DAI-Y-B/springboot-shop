package com.turing.mapper;

import com.turing.entity.Vip;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface VipMapper {
    /** 根据用户id查询对应用户的vip信息 */
    @Select("select * from easybuy_vip where vip_user_id = #{vipUserId}")
    Vip selectVipByuser(Vip vip);

    /** 根据用户id删除对应用户的vip信息 */
    @Delete("delete from easybuy_vip where vip_user_id = #{vipUserId}")
    int deleteVipByUserId(Vip vip);
}
//easybuy_vip
//vip_id             int primary key auto_increment 	/*会员编号*/,
//        int(20) 	/*用户编号*/,
//vip_start_time     datetime 	/*vip开始时间*/,
//vip_end_time       datetime 	/*vip结束时间*/
//
//vipId             int primary key autoIncrement 	/*会员编号*/,
//vipUserId        int(20) 	/*用户编号*/,
//vipStartTime     datetime 	/*vip开始时间*/,
//vipEndTime       datetime 	/*vip结束时间*/