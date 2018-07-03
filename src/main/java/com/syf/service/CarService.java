package com.syf.service;

import com.syf.bean.Car;
import com.syf.dao.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    CarDao carDao;

    @Autowired
    public CarService(CarDao dao){
        this.carDao = dao;
    }

    public Car getAvailCar(){
        return carDao.getAvailableCar();
    }

    public void save(Car car){
        this.carDao.addCar(car);
    }
}
