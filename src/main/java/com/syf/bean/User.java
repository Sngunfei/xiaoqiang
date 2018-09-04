package com.syf.bean;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "user", schema = "xq")
public class User extends BasePO{

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", length = 256)
    private String nickname;

    @Id
    @Column(name = "account", nullable = false, unique = true)
    private String account;

    @Column(name = "ip")
    private String ip;

    @Column(name = "email", length = 256)
    private String email;

    public User() {}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
