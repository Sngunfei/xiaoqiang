package com.syf.bean;

import com.syf.Const.Config;
import com.syf.Const.Status;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Component
@Entity
@Table(name = "task" , schema = "xq")
public class Task extends BasePO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "address", nullable = false)
    private int addressID;

    @Column(name = "user", nullable = false)
    private String user;

    @Column(name = "car")
    private int carID;

    @Column(name = "startTime", nullable = false)
    private Timestamp startTime;

    @Column(name = "deliverTime")
    private Timestamp deliverTime;

    @Column(name = "finishTime")
    private Timestamp finishTime;

    @Column(name = "cooperation")
    private String cp;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "assign")
    private boolean assign;

    public Task() {
        this.startTime = new Timestamp(System.currentTimeMillis());
        this.status = Status.Ready;
    }

    public Task(int id, int address, String user) {
        this.startTime = new Timestamp(System.currentTimeMillis());
        this.status = Status.Ready;
        this.id = id;
        this.addressID = address;
        this.user = user;
    }

    public boolean isAssign(){
        return assign;
    }

    public int getID() {
        return id;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Timestamp deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAssign(boolean assign) {
        this.assign = assign;
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
