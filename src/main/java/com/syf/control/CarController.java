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

import javax.servlet.http.HttpServletRequest;
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
    public void addCarDo(HttpServletRequest request, HttpServletResponse response){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
        String ip = request.getParameter("ip");
        System.out.println(ip);
        int port = Integer.valueOf(request.getParameter("port"));
        System.out.println(port);
        if(carService.isExist(ip))
            return ;
        try{
            float x = Float.valueOf(request.getParameter("x"));
            float y = Float.valueOf(request.getParameter("y"));
            float z = Float.valueOf(request.getParameter("z"));
            float ax = Float.valueOf(request.getParameter("ax"));
            float ay = Float.valueOf(request.getParameter("ay"));
            float az = Float.valueOf(request.getParameter("az"));
            float aw = Float.valueOf(request.getParameter("aw"));
            String model = request.getParameter("model");
            Car car = new Car(ip, port);
            car.setLocation(x, y, z);
            car.setPose(ax, ay, az, aw);
            car.setModel(model);
            carService.save(car);
            System.out.println(car.toString());
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

        String status = "default";
        switch (car.getStatus()){
            case 0: status = "已就绪";break;
            case 1: status = "正在配送"; break;
            case 2: status = "正在返程"; break;
            case 3: status = "故障ing"; break;
        }
        mav.addObject("status", status);
        return mav;
    }
}
