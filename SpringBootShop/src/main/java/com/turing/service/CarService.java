package com.turing.service;


import com.turing.entity.Car;

import java.util.List;

public interface CarService {

    /**根据用户id查询所有的物品*/
    List<Car> selectAllUser(Car car);

    /** 加入购物车 */
    int addCar(Car car);

    /**根据id查询是否存在相同的商品*/
    Car selectCarById(Car car);

    /**根据购物车物品id修改属性值*/
    int updateCarInfo(Car car);

    /**根据商品id删除对应购物车中的商品*/
    int deleteCar(Car car);

    /** 根据用户Id删除对应购物车 */
    int deleteOneSelf(Car car);

}
