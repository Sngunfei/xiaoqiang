package com.syf.control;

import com.syf.bean.Car;
import com.syf.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/car")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService service){
        this.carService = service;
    }

    @RequestMapping(value = "/newCar", method = RequestMethod.GET)
    public String newCar(){
        return "newCar";
    }

    @RequestMapping(value = "/addCar.do", method = RequestMethod.POST)
    public String addCarDo(@RequestParam("host") String ip, @RequestParam("port") String port){
        Car car = new Car();
        car.setIp(ip);
        car.setPort(Integer.valueOf(port));
        car.setStatus("ready");
        carService.save(car);
        return "main";
    }

    @RequestMapping(value = "/carManage", method = RequestMethod.GET)
    public String carManager(){
        return "carManage";
    }
}
