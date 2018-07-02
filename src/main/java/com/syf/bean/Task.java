package com.syf.bean;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;

@Component
@Entity
@Table(name = "task" , schema = "xq")
public class Task extends BasePO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskID", unique = true)
    private int id;

    @Column(name = "destination", nullable = false)
    private int destination;

    @Column(name = "userID", nullable = false)
    private int userID;

    @Column(name = "carID")
    private int carID;

    @Column(name = "startTime", nullable = false)
    private Date startTime;

    @Column(name = "finishTime")
    private Date finishTime;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "deliverTime")
    private Date deliverTime;

    public Task() {
        this.startTime = new Date(System.currentTimeMillis());
    }

    public Task(int id, int destination, int userID, String status) {
        this.id = id;
        this.destination = destination;
        this.userID = userID;
        this.startTime = new Date(System.currentTimeMillis());
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }
}
