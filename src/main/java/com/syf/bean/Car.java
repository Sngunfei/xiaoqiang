package com.syf.bean;

import com.syf.Const.Status;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "car", schema = "xq")
public class Car extends BasePO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "ip")
    private String ip;

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

    @Column(name = "curTask")
    private int curTask;

    public void setCurTask(int taskID) {
        this.curTask = taskID;
    }

    public int getCurTask(){
        if(Status.Ready.equals(status))
            return curTask;
        else
            return -1;
    }

    public Car(){
        this.status = Status.Ready;
        this.setPose(0, 0, 0, 0);
        this.setLocation(0, 0, 0);
    }

    public Car(String ip){
        this.ip = ip;
        this.status = Status.Ready;
        this.setPose(0, 0, 0, 0);
        this.setLocation(0, 0, 0);
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

    public int getID() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public boolean isRunning(){
        return Status.Running.equals(this.status);
    }

    public boolean isReady(){
        return Status.Ready.equals(this.status);
    }

    public boolean isDone(){
        return Status.Done.equals(this.status);
    }

    public boolean isFail(){
        return Status.Fail.equals(this.status);
    }
}
