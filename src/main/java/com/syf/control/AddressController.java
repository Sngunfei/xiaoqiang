package com.syf.control;

import com.syf.bean.Address;
import com.syf.service.AddressService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/place")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService service){
        this.addressService = service;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addPlaceDo(HttpServletRequest request, HttpServletResponse response){
        Address place = new Address();
        place.setAddress(request.getParameter("address"));
        place.setLocX(Float.valueOf(request.getParameter("x")));
        place.setLocY(Float.valueOf(request.getParameter("y")));
        place.setLocZ(Float.valueOf(request.getParameter("z")));
        place.setAngX(Float.valueOf(request.getParameter("ax")));
        place.setAngY(Float.valueOf(request.getParameter("ay")));
        place.setAngZ(Float.valueOf(request.getParameter("az")));
        place.setAngW(Float.valueOf(request.getParameter("aw")));
        addressService.save(place);
        response.setStatus(200);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public ModelAndView placeManage(){
        ModelAndView mav = new ModelAndView("addressManage");
        List<Address> addresses = addressService.getAllAddress();
        mav.addObject("addresses", addresses);
        return mav;
    }

}
