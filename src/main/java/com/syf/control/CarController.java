package com.syf.control;

import com.syf.Const.Status;
import com.syf.bean.Car;
import com.syf.bean.Task;
import com.syf.biz.logger;
import com.syf.service.CarService;
import com.syf.service.TaskService;
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
import java.util.List;

@Controller
@RequestMapping(value = "/car")
public class CarController {

    private CarService carService;
    private TaskService taskService;

    @Autowired
    public CarController(CarService service1, TaskService service2){
        this.carService = service1;
        this.taskService = service2;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(HttpServletRequest request, HttpServletResponse response){
        String ip = request.getParameter("ip");
        String port = request.getParameter("port");
        String inetAddress = String.format("%s:%s", ip, port);
        if(carService.isExist(inetAddress))
            return ;

        float x = Float.valueOf(request.getParameter("x"));
        float y = Float.valueOf(request.getParameter("y"));
        float z = Float.valueOf(request.getParameter("z"));
        float ax = Float.valueOf(request.getParameter("ax"));
        float ay = Float.valueOf(request.getParameter("ay"));
        float az = Float.valueOf(request.getParameter("az"));
        float aw = Float.valueOf(request.getParameter("aw"));
        String model = request.getParameter("model");
        Car car = new Car(inetAddress);
        car.setLocation(x, y, z);
        car.setPose(ax, ay, az, aw);
        car.setModel(model);
        carService.save(car);

    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public ModelAndView manage(){
        ModelAndView mav = new ModelAndView("carManage");
        mav.addObject("cars", carService.getAllCar());
        mav.addObject("statistics", carService.getCarStatistics());
        return mav;
    }

    @RequestMapping(value = "/testConnect", method = RequestMethod.POST)
    public void testConnect(@RequestParam("ip") String ip, @RequestParam("port") int port,
                            HttpServletResponse response){
        try {
            Socket socket = new Socket(ip, port);
            logger.info(String.format("connect to the car successfully, %s:%d. /n", ip, port));
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
            socket.setSoTimeout(5000); // 五秒后超时
            try {
                pw.println("200");
                String ack = br.readLine();
                if("200".equals(ack))
                    response.getWriter().print("{\"success\":true}");
            }catch (InterruptedIOException e){
                logger.debug("Test connect failed: ack timeout.");
                response.getWriter().print("{\"success\":true}");
            }
            response.getWriter().flush();
            response.getWriter().close();
            br.close();
            pw.close();
            socket.close();
        }catch (IOException e){
            logger.debug("Test connect failed: can't connect to socket. ");
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/carInfo-{id}", method = RequestMethod.GET)
    public ModelAndView showCar(@PathVariable("id") int carId){
        ModelAndView mav = new ModelAndView("carInfo");
        Car car = carService.getCar(carId);
        if(car.isRunning()){
            Task task = taskService.getTask(car.getCurTask());
            mav.addObject("task", task);
        }
        mav.addObject("car", car);
        mav.addObject("status", car.getStatus().content);
        return mav;
    }

    @RequestMapping(value = "/car-{status}", method = RequestMethod.GET)
    public ModelAndView showCarByStatus(@PathVariable String status){
        ModelAndView mav = new ModelAndView("classifyCars");
        List<Car> cars;
        if("all".equals(status))
            cars = carService.getAllCar();
        else
            cars = carService.classifyCarsByStatus(status);
        mav.addObject("cars", cars);
        mav.addObject("status", status);
        return mav;
    }
}
