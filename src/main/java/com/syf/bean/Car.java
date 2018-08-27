package com.syf.bean;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "car", schema = "xq")
public class Car extends BasePO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carID")
    private int carID;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "ip")
    private String ip;

    @Column(name = "port")
    private int port;

    @Column(name = "x")
    private float x;

    @Column(name = "y")
    private float y;

    @Column(name = "z")
    private float z;

    @Column(name = "ax")
    private float ax;

    @Column(name = "ay")
    private float ay;

    @Column(name = "az")
    private float az;

    @Column(name = "aw")
    private float aw;

    @Column(name = "model")
    private String model;

    public Car(){

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getAx() {
        return ax;
    }

    public void setAx(float ax) {
        this.ax = ax;
    }

    public float getAy() {
        return ay;
    }

    public void setAy(float ay) {
        this.ay = ay;
    }

    public float getAz() {
        return az;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setAz(float az) {
        this.az = az;
    }

    public float getAw() {
        return aw;
    }

    public void setAw(float aw) {
        this.aw = aw;
    }

    public Car(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setLocation(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setPose(float ax, float ay, float az, float aw){
        this.ax = ax;
        this.ay = ay;
        this.az = az;
        this.aw = aw;
    }
}
