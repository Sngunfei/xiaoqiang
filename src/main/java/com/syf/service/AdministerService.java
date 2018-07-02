package com.syf.service;

import com.syf.dao.AdministerDao;
import com.syf.bean.Administer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class AdministerService {

    private final AdministerDao dao;

    @Autowired
    public AdministerService(AdministerDao dao) {
        this.dao = dao;
    }

    public AdministerDao getDao() {
        return dao;
    }

    // 新管理员注册
    public void register(Administer admin){
        dao.save(admin);
    }

    // 根据账户拿到admin
    public Administer getAdminister(String account){
        return dao.getAdminByAccount(account);
    }

    // 更新管理员信息
    public void updateAdminister(Administer admin){
        dao.updateAdmin(admin);
    }

    // 删除该管理员
    public void deleteAdmin(Administer admin){
        dao.deleteAdmin(admin.getAccount());
    }

}
