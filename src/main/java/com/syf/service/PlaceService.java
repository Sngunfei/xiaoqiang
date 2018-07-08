package com.syf.service;

import com.syf.bean.Place;
import com.syf.dao.PlaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceService {

    private PlaceDao placeDao;

    @Autowired
    public PlaceService(PlaceDao dao){
        this.placeDao = dao;
    }

    public boolean isExist(String place){
        return placeDao.isExist(place);
    }

    public Place getPlaceByAddr(String place){
        return placeDao.getPlaceByAddr(place);
    }

    public String getDescripById(int placeId){
        return placeDao.getPlaceById(placeId).getDescription();
    }

    public int addrToId(String place){
        return placeDao.addrToId(place);
    }

    public Place getPlaceById(int id){
        return placeDao.getPlaceById(id);
    }

    public void save(Place place){
        placeDao.save(place);
    }
}
