package com.turing.mapper;

import com.turing.entity.Car;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface CarMapper {

    /** 根据用户id查询所有的物品 */
    @Select("select * from easybuy_car where user_id = #{userId}")
    @Results(id = "replyAndUsers", value = {@Result(column = "car_id", property = "carId"),
            @Result(column = "user_id", property = "userId"), @Result(column = "product_id", property = "productId"),
            @Result(column = "product_count", property = "productCount"),
            @Result(column = "product_id", property = "products", many = @Many(fetchType = FetchType.LAZY, select = "com.turing.mapper.ProductMapper.selectProductInfoById")),})
    List<Car> selectAllUser(Car car);

    /** 加入购物车 */
    @Insert("insert into easybuy_car values(null,#{userId},#{productId},#{productCount})")
    @Options(useGeneratedKeys = true)
    // 自动增长列
    int addCar(Car car);

    /** 根据id查询是否存在相同的商品 */
    @Select("select * from easybuy_car where product_id = #{productId}")
    Car selectCarById(Car car);

    /** 根据购物车物品id修改属性值 */
    @Update("update easybuy_car set user_id=#{userId}, product_id =#{productId}, product_count = #{productCount} where car_id = #{carId} ")
    int updateCarInfo(Car car);

    /** 根据商品id删除对应购物车中的商品(可用与当用户在购买完商品之后删除对应的商品) */
    @Delete("delete from easybuy_car where product_id = #{productId} ")
    int deleteCar(Car car);

    /** 根据用户Id删除对应购物车 */
    @Delete("delete from easybuy_car where user_id = #{userId}")
    int deleteOneSelf(Car car);

}
// car_id int not null primary key auto_increment,/* 购物车编号*/
// user_id int(100) ,/* 购物车对应用户*/
// product_id int(100) , /* 产品Id*/
// product_count int(100)/* 购买数量*/
//
// carId int not null primary key autoIncrement,/* 购物车编号*/
// userId int(100) ,/* 购物车对应用户*/
// productId int(100) , /* 产品id*/
// productCount int(100)/* 购买数量*/