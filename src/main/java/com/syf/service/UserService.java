package com.syf.service;

import com.syf.bean.User;
import com.syf.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserDao userDao;

    @Autowired
    public UserService(UserDao dao){
        userDao = dao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public boolean isExist(String account){
        User user = userDao.get(account);
        return user != null;
    }

    public User getUserByAccount(String account){
        return userDao.get(account);
    }

    public void save(User user){
        userDao.save(user);
    }


    public List<User> getAllAccounts(){
        return userDao.getAllAccounts();
    }

}
