package com.syf.service;

import com.syf.bean.User;
import com.syf.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserDao dao;

    @Autowired
    public UserService(UserDao dao){
        this.dao = dao;
    }

    public UserDao getDao() {
        return dao;
    }

    public boolean isExist(String account){
        User user = dao.getUserByAccount(account);
        return user != null;
    }

    public User getUserByAccount(String account){
        return dao.getUserByAccount(account);
    }

    public void save(User user){
        dao.save(user);
    }

    public User getUserById(int id){
        return dao.getUserById(id);
    }

    public String getAccountById(int id){
        return dao.getUserById(id).getAccount();
    }

    public List<User> getAllAccounts(){
        return dao.getAllAccounts();
    }

}
