package com.syf.control;

import com.syf.bean.Place;
import com.syf.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/place")
public class PlaceController {

    private PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService service){
        this.placeService = service;
    }

    @RequestMapping(value = "/newPlace", method = RequestMethod.GET)
    public String addPlace(){
        return "newPlace";
    }

    @RequestMapping(value = "/addPlace.do", method = RequestMethod.POST)
    public String addPlaceDo(HttpServletRequest request){

        Place place = new Place();
        place.setDescription(request.getParameter("descrip"));
        place.setLocX(Float.valueOf(request.getParameter("locx")));
        place.setLocY(Float.valueOf(request.getParameter("locy")));
        place.setLocZ(Float.valueOf(request.getParameter("locz")));
        System.out.println(place.toString());
        placeService.save(place);
        return "main";
    }
}
