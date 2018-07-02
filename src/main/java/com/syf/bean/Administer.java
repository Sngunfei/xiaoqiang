package com.syf.bean;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import javax.persistence.*;

@Component
@Entity
@Table(name = "administer", schema = "xq")
public class Administer extends BasePO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int adminID;

    @Column(name = "account", unique = true)
    private String account;

    @Column(name = "password")
    private String password;

    @Column(name = "ip")
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
