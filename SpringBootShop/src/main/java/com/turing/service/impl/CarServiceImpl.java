package com.turing.service.impl;

import com.turing.entity.Car;
import com.turing.mapper.CarMapper;
import com.turing.service.CarService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    Logger logger = Logger.getLogger(CarServiceImpl.class);
    @Autowired
    private CarMapper carMapper;

    public int addCar(Car car) {
        logger.info("调用CarServiceImpl类的addCar方法,将商品添加到购物车");
        int i = carMapper.addCar(car);
        return i;
    }

    public Car selectCarById(Car car) {
        logger.info("调用CarServiceImpl类的selectCarById方法,根据id查询是否存在相同的商品");
        Car carInfoCar = carMapper.selectCarById(car);
        return carInfoCar;
    }

    public int updateCarInfo(Car car) {
        logger.info("调用CarServiceImpl类的updateCarInfo方法,根据购物车物品id修改属性值");
        int i = carMapper.updateCarInfo(car);
        return i;
    }

    public List<Car> selectAllUser(Car car) {
        logger.info("调用CarServiceImpl类的selectAllUser方法,根据用户id查询所有的物品");
        List<Car> userCars = carMapper.selectAllUser(car);
        return userCars;
    }

    public int deleteCar(Car car) {
        logger.info("调用CarServiceImpl类的deleteCar方法,根据商品id删除对应的商品");
        return carMapper.deleteCar(car);
    }

    public int deleteOneSelf(Car car) {
        logger.info("调用CarServiceImpl类的deleteOneSelf方法,根据用户Id删除对应购物车");
        return carMapper.deleteOneSelf(car);
    }

}
