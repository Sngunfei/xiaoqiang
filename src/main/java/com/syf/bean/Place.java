package com.syf.bean;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "destination", schema = "xq")
public class Place extends BasePO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "desID")
    private int desID;

    @Column(name = "description", nullable = false, length = 512)
    private String description;

    @Column(name = "locX", nullable = false, precision = 2)
    private float locX;

    @Column(name = "locY", nullable = false, precision = 2)
    private float locY;

    @Column(name = "locZ", nullable = false, precision = 2)
    private float locZ;

    public int getDesID() {
        return desID;
    }

    public void setCarID(int desID) {
        this.desID = desID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String decription) {
        this.description = decription;
    }

    public float getLocX() {
        return locX;
    }

    public void setLocX(float locX) {
        this.locX = locX;
    }

    public float getLocY() {
        return locY;
    }

    public void setLocY(float locY) {
        this.locY = locY;
    }

    public float getLocZ() {
        return locZ;
    }

    public void setLocZ(float locZ) {
        this.locZ = locZ;
    }
}
