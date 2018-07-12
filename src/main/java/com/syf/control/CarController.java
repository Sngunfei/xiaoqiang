package com.syf.control;

import com.syf.bean.Car;
import com.syf.bean.Task;
import com.syf.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Socket;


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
    public void addCarDo(@RequestParam("ip") String ip, @RequestParam("port") int port,
                         HttpServletResponse response){
        if(carService.isExist(ip))
            return ;
        try{
            carService.save(ip, port);
            response.getWriter().print("{\"success\":true}");
            response.getWriter().flush();
            response.getWriter().close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/carManage", method = RequestMethod.GET)
    public ModelAndView carManager(){
        ModelAndView mav = new ModelAndView("carManage");
        mav.addObject("cars", carService.getAllCar());
        mav.addObject("carInfo", carService.getCarInfo());
        return mav;
    }

    @RequestMapping(value = "/testConnect.do", method = RequestMethod.GET)
    public void testConnect(@RequestParam("ip") String ip, @RequestParam("port") int port,
                            HttpServletResponse response){
        // 测试与小车的连接
        try {
            Socket socket = new Socket(ip, port);
            PrintWriter pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
            pw.println("connect");
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            socket.setSoTimeout(5000); // 五秒后超时
            boolean timeout = true;
            try {
                String ack = br.readLine();
                timeout = false;
            }catch (InterruptedIOException e){
                timeout = true;
            }

            if(!timeout){
                response.getWriter().print("{\"success\":true}");
                response.getWriter().flush();
                response.getWriter().close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/carInfo-{id}", method = RequestMethod.GET)
    public ModelAndView showCar(@PathVariable("id") int carId){
        ModelAndView mav = new ModelAndView("carInfo");
        Car car = carService.getCarById(carId);
        mav.addObject("car", car);
        // 如果状态为1(delivering), 那么就把该任务信息也展示出来
        if(car.getStatus() == 1){
            // ToDO: car信息里没有保存taskid, task里才有carid, 在不重构数据库的情况下，需要从task表里找，效率较低
        }

        return mav;
    }
}
