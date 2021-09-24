package com.turing.mapper;

import com.turing.entity.OrderDetail;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

@Mapper
public interface OrderDetailMapper {

    /** 创建单明细表 */
    @Insert("insert into easybuy_order_detail values(null,#{eoId},#{epId},#{eodQuantity},#{eodCost})")
    @Options(useGeneratedKeys = true)
    // 自动增长列
    int addOrderDatail(OrderDetail orderDetail);

    /**查询订单信息*/
    @Select("select * from easybuy_order_detail where eo_id = #{id}")
    @Results(id = "orderInfo", value = {@Result(column = "eod_id", property = "eodId"),
            @Result(column = "eo_id", property = "eoId"),
            @Result(column = "ep_id", property = "epId"),
            @Result(column = "eod_quantity", property = "eodQuantity"),
            @Result(column = "eod_cost", property = "eodCost"),
            @Result(column = "ep_id", property = "products", many = @Many(fetchType = FetchType.LAZY, select = "com.turing.mapper.ProductMapper.selectProductInfoById")),})
    OrderDetail selectByOrderId(@Param("id") int id);

    /**删除对应id的订单信息*/
    @Delete("delete from easybuy_order_detail where eo_id =#{eoId}")
    int deleteOrderInfo(OrderDetail orderDetail);
}
//easybuy_order_detail
//eod_id int not null primary key auto_increment,/* */
//eo_id int not null,/* 订单ID */
//ep_id int not null,/* 商品ID */
//eod_quantity int(6) not null,/* 订单数量 */
//eod_cost decimal(12,2) not null/* 金额 */
//
//eodId int not null primary key autoIncrement,/* */
//eoId int not null,/* 订单id */
//epId int not null,/* 商品id */
//eodQuantity int(6) not null,/* 订单数量 */
//eodCost decimal(12,2) not null/* 金额 */