package com.syf.control;

import com.syf.bean.Place;
import com.syf.service.PlaceService;
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
public class PlaceController {

    private PlaceService placeService;

    private static Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    public PlaceController(PlaceService service){
        this.placeService = service;
    }

    @RequestMapping(value = "/addPlace.do", method = RequestMethod.POST)
    public void addPlaceDo(HttpServletRequest request, HttpServletResponse response){
        Place place = new Place();
        place.setDescription(request.getParameter("descrip"));
        place.setLocX(Float.valueOf(request.getParameter("x")));
        place.setLocY(Float.valueOf(request.getParameter("y")));
        place.setLocZ(Float.valueOf(request.getParameter("z")));
        place.setAngX(Float.valueOf(request.getParameter("x1")));
        place.setAngY(Float.valueOf(request.getParameter("y1")));
        place.setAngZ(Float.valueOf(request.getParameter("z1")));
        place.setAngW(Float.valueOf(request.getParameter("w1")));
        //System.out.println(place.toString());
        placeService.save(place);
        try{
            response.getWriter().print("{\"success\":true}");
            response.getWriter().flush();
            response.getWriter().close();
        }catch (IOException e){
            e.printStackTrace();
        }
        logger.info("Add new place success!");
    }

    @RequestMapping(value = "/placeManage", method = RequestMethod.GET)
    public ModelAndView placeManager(){
        ModelAndView mav = new ModelAndView("placeManage");
        List<Place> allPlaces = placeService.getAllPlaces();
        System.out.println("!!!!!!!"+allPlaces.size());
        mav.addObject("places", allPlaces);
        return mav;
    }
}
