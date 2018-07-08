package com.syf.bean;

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

    /*
        - status
            0 : ready: 只是存在数据库里，尚未开始
            1 : delivering : 已经开始，正在进行中
            2 : done : 已经正常完成
            3 : err : 由于种种原因导致任务失败
            .. etc
     */
    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "deliverTime")
    private Date deliverTime;

    public Task() {
        this.startTime = new Date(System.currentTimeMillis());
    }

    public Task(int id, int destination, int userID) {
        this.id = id;
        this.destination = destination;
        this.userID = userID;
        this.startTime = new Date(System.currentTimeMillis());
        this.status = 0;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

}
