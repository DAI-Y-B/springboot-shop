package com.turing.mapper;

import com.turing.entity.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface OrderMapper {
    /** 创建订单 */
    @Insert("insert into easybuy_order values(null,#{eoUserId},#{eoUserName},#{eoUserAddress},#{eoCreateTime},#{eoCost},#{eoStatus},#{eoType})")
    @Options(useGeneratedKeys = true)
    // 自动增长列
    int addOrder(Order order);

    /** 查询最新创建的订单 */
    @Select("select * from easybuy_order order by eo_id DESC limit 1 ")
    Order selectLastOrder();

    /** 查询所有的订单 */
    @Select("select * from easybuy_order ")
    @Results(id = "orderInfo", value = {@Result(column = "eo_id", property = "eoId"),
            @Result(column = "eo_user_id", property = "eoUserId"),
            @Result(column = "eo_user_name", property = "eoUserName"),
            @Result(column = "eo_user_address", property = "eoUserAddress"),
            @Result(column = "eo_create_time", property = "eoCreateTime"),
            @Result(column = "eo_cost", property = "eoCost"), @Result(column = "eo_status", property = "eoStatus"),
            @Result(column = "eo_type", property = "eoType"),
            @Result(column = "eo_id", property = "orderDetails", many = @Many(fetchType = FetchType.LAZY, select = "com.turing.mapper.OrderDetailMapper.selectByOrderId")),
            @Result(column = "eo_status", property = "ostatuss", many = @Many(fetchType = FetchType.LAZY, select = "com.turing.mapper.OstatusMapper.selectByIdOstatus")),})
    List<Order> selectAllOrders();

    /** 分页查询 */
    @Select("select * from easybuy_order limit #{currentPage},#{size}")
    @ResultMap(value = "orderInfo")
    List<Order> selectAllOrdersPerPage(@Param("currentPage") Integer currentPage, @Param("size") Integer size);

    /** 根据订单Id查询对应的订单 */
    @Select("select * from easybuy_order where eo_id =#{eoId}")
    @ResultMap(value = "orderInfo")
    Order selectById(Order order);

    /** 根据订单Id修改订单信息 */
    @Update("update easybuy_order set eo_user_id = #{eoUserId}, eo_user_name = #{eoUserName}, eo_user_address = #{eoUserAddress}, "
            + "eo_create_time = #{eoCreateTime}, eo_cost = #{eoCost}, eo_status = #{eoStatus} where eo_id =#{eoId}")
    int updateOrderInfo(Order order);

    /** 删除对应id的订单信息 */
    @Delete("delete from easybuy_order where eo_id =#{eoId}")
    int deleteOrderInfo(Order order);

    /** 根据用户名查询订单 */
    @Select("select * from easybuy_order where eo_user_name =#{eoUserName}")
    List<Order> selectByUserName(Order order);

    /** 根据订单Id查询对应的订单 */
    @Select("select * from easybuy_order where eo_user_id =#{eoUserId}")
    @ResultMap(value = "orderInfo")
    List<Order> selectOrdersById(Order order);

    /** 根据订单Id和用户姓名一查询 */
    @Select("select * from easybuy_order where eo_user_name =#{eoUserName} and eo_id =#{eoId}")
    Order selectByNameAndId(Order order);

    /** 根据用户id删除用户的购物车 */
    @Delete("delete from easybuy_order where eo_user_id = #{eoUserId}")
    int deleteOneSelf(Order order);

    /** 用户个人分页查询 */
    @Select("select * from easybuy_order where eo_user_id=#{eoUserId}  order by eo_create_time DESC limit #{currentPage},#{size}")
    @ResultMap(value = "orderInfo")
    List<Order> selectAllOrdersPerPageByUserId(@Param("eoUserId") Integer eoUserId, @Param("currentPage") Integer currentPage, @Param("size") Integer size);

}
// easybuy_order
// eo_id int not null primary key auto_increment,/* 订单Id */
// eo_user_id varchar(10) not null,/* 订单用户ID */
// eo_user_name varchar(20),/* 订单用户姓名（接收人） */
// eo_user_address varchar(200),/* 订单地址（接受地址） */
// eo_create_time date not null,/* 订单创建时间 */
// eo_cost decimal(12,2) not null,/* 订单金额 */
// eo_status int not null,/* 1 下单 2 审核通过 3 配货 4 送货中 5收获并确认 */
// eo_type int not null/* （付款方式）1 货到付款 2 网上支付 */
//
// eoId int not null primary key autoIncrement,/* 订单id */
// eoUserId varchar(10) not null,/* 订单用户id */
// eoUserName varchar(20),/* 订单用户姓名（接收人） */
// eoUserAddress varchar(200),/* 订单地址（接受地址） */
// eoCreateTime date not null,/* 订单创建时间 */
// eoCost decimal(12,2) not null,/* 订单金额 */
// eoStatus int not null,/* 1 下单 2 审核通过 3 配货 4 送货中 5收获并确认 */
// eoType int not null/* （付款方式）1 货到付款 2 网上支付 */