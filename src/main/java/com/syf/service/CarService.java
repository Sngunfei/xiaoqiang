package com.syf.service;

import com.syf.Const.Parameters;
import com.syf.bean.Car;
import com.syf.dao.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    CarDao carDao;

    @Autowired
    public CarService(CarDao dao){
        this.carDao = dao;
    }

    public Car getAvailableCar(){
        List<Car> cars = carDao.getAllcar();
        for(Car car: cars){
            if(car.getStatus() == 0)
                return car;
        }
        return null;
    }

    public int[] getCarInfo(){
        List<Car> cars = carDao.getAllcar();
        int[] info = new int[5];
        info[0] = cars.size();
        for(Car car : cars){
            switch (car.getStatus()){
                case 0: info[1]++; break;  // ready
                case 1: info[2]++; break;  // going
                case 2: info[3]++; break;  // backing
                case 3: info[4]++; break;  // error
                default:
            }
        }
        return info;
    }

    public void save(String ip, int port) {
        Car car = new Car();
        car.setStatus(0);
        car.setPort(port);
        car.setIp(ip);
        carDao.addCar(car);
    }

    public void save(Car car){
        carDao.addCar(car);
    }


    public List<Car> getAllCar(){
        return carDao.getAllcar();
    }

    // 监听端口，获取小车信息, 存在哪里还没想清楚
    public void receiveCarInfo(){
        try {
            ServerSocket server = new ServerSocket();
            InetSocketAddress address = new InetSocketAddress(Parameters.IP, Parameters.PORT);
            server.bind(address, Parameters.MAX_CONN);

            Socket client = server.accept();
            String carAddr = client.getInetAddress().getHostAddress();
            int port = client.getPort();

            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String info = br.readLine();

            System.out.println(carAddr + " " + port + " " + info);

            client.close();
            server.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean isExist(String ip){
        return carDao.getCarByIp(ip) != null;
    }

    public void updateCar(Car car){
        carDao.updateCar(car);
    }

    public Car getCarById(int carId){
        return carDao.getCarById(carId);
    }

    public List<Car> getCarByStatus(int status){
        List<Car> allCars = getAllCar();
        List<Car> cars = new ArrayList<>();
        for(Car car: allCars){
            if(car.getStatus() == status){
                cars.add(car);
            }
        }
        return cars;
    }
}
