package com.syf.service;

import com.syf.bean.Car;
import com.syf.dao.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void save(String ip, int port){
        Car car = new Car();
        car.setIp(ip);
        car.setPort(port);
        car.setStatus(0);
        this.carDao.addCar(car);
    }

    public List<Car> getAllCar(){
        return carDao.getAllcar();
    }

    public Car getCarById(int carID){
        return carDao.getCarById(carID);
    }

    public int[] carInfo(){
        List<Car> cars = getAllCar();
        int[] info = new int[4];
        info[0] = cars.size();
        for(Car car : cars){
            switch (car.getStatus()){
                case 1: info[1]++; break;
                case 2: info[2]++; break;
                case 3: info[3]++; break;
                default:
            }
        }
        return info;
    }

    public void updateCar(Car car){
        carDao.updateCar(car);
    }
}
