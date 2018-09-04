package com.syf.service;

import com.syf.bean.Address;
import com.syf.biz.logger;
import com.syf.dao.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private AddressDao placeDao;

    @Autowired
    public AddressService(AddressDao dao){
        this.placeDao = dao;
    }

    public boolean isExist(String address){
        if(address == null || address.equals(""))
            return false;
        return placeDao.getAddress(address) != null;
    }

    public int getAddressID(String des){
        Address address = getAddress(des);
        return address == null ? -1: address.getId();
    }

    public Address getAddress(String address){
        return placeDao.getAddress(address);
    }

    public Address getAddress(int addressID){
        return placeDao.getAddress(addressID);
    }

    private boolean checkAddress(Address address){
        if(address == null)
            return false;
        if(address.getId() <= 0)
            return false;
        if(address.getAddress() == null || "".equals(address.getAddress()))
            return false;
        return true;
    }

    public void updateAddress(Address address){
        if(!checkAddress(address)){
            logger.error(String.format("Invalid Address: %s",
                    address == null ? "null" : address.toString()));
            return;
        }
        if(getAddress(address.getId()) == null){
            logger.error(String.format("The address does not exist in Mysql. %s", address.toString()));
            return;
        }
        placeDao.saveOrUpdate(address);
    }

    public void deleteAddress(Address address){
        placeDao.delete(address);
    }

    public void save(Address address){
        if(checkAddress(address))
            placeDao.save(address);
        else
            logger.error("Invalid Address. ");
    }

    public List<Address> getAllAddress(){
        return placeDao.getAllAddress();
    }

}
