package com.syf.service;

import com.syf.Const.Config;
import com.syf.Const.Status;
import com.syf.bean.Car;
import com.syf.biz.logger;
import com.syf.dao.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private CarDao carDao;

    @Autowired
    public CarService(CarDao dao){
        this.carDao = dao;
    }


    /**
     * @return 返回一台当前空闲的车
     */
    Car getAvailableCar(){
        return carDao.getOne();
    }

    /**
     * @return int[] 车辆总数, 空闲数量, 配送数量, 故障数量
     */
    public int[] getCarStatistics(){
        List<Car> cars = carDao.getAllCar();
        int[] info = new int[5];
        info[0] = cars.size();
        for(Car car : cars){
            Status status = car.getStatus();
            if(Status.Ready.equals(status))
                info[1]++;
            else if(Status.Running.equals(status))
                info[2]++;
            else if(Status.Done.equals(status))
                info[3]++;
            else if(Status.Fail.equals(status))
                info[4]++;
            else
                logger.error(String.format("Invalid car%s status: %s", car.getID(), status));
        }
        return info;
    }

    private boolean checkCar(Car car){
        if(car == null)
            return false;
        if(car.getID() <= 0)
            return false;
        if(!Utils.checkStatus(car.getStatus()))
            return false;
        if(car.getIp() == null || "".equals(car.getIp()))
            return false;
        return true;
    }

    public void save(Car car){
        if(!checkCar(car)){
            logger.error(String.format("Invalid Car: %s",
                    car == null ? "null" : car.toString()));
            return;
        }
        carDao.save(car);
    }

    public List<Car> getAllCar(){
        return carDao.getAllCar();
    }


    /**
     * @param ip ipv4地址
     * @return 该小车是否存在, 防止重复添加添加相同ip的小车。
     */
    public boolean isExist(String ip){
        return carDao.getCarByIp(ip) != null;
    }

    public void updateCar(Car car){
        if(car != null && carDao.get(car.getID()) != null) {
            if (checkCar(car))
                carDao.update(car);
            else
                logger.error(String.format("Invalid car: %s", car.toString()));
        }else
            logger.error("The car does not exist in Mysql.");

    }

    public Car getCar(int carID){
        return carDao.get(carID);
    }

    public void deleteCar(int carID){
        carDao.delete(carID);
    }

    public void deleteCar(Car car){
        carDao.delete(car);
    }

    public List<Car> classifyCarsByStatus(String status){
        List<Car> allCars = getAllCar();
        List<Car> cars = new ArrayList<>();
        if(!Utils.checkStatus(status)){
            logger.error(String.format("Invalid status: %s", status));
            return cars;
        }
        for(Car car: allCars)
            if(car.getStatus().equals(status))
                cars.add(car);
        return cars;
    }

    public void refreshCar(int carID){
        refreshCar(getCar(carID));
    }

    public void refreshCar(Car car){
        if(car.isReady())
            return;
        car.setStatus(Status.Ready);
        car.setCurTask(-1);
    }
}
