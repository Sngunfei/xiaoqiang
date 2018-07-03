package com.syf.bean;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "place", schema = "xq")
public class Place extends BasePO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "desID")
    private int id;

    @Column(name = "description", nullable = false, length = 512)
    private String description;

    @Column(name = "locX", nullable = false, precision = 2)
    private float locX;

    @Column(name = "locY", nullable = false, precision = 2)
    private float locY;

    @Column(name = "locZ", nullable = false, precision = 2)
    private float locZ;

    @Column(name = "angX", nullable = false, precision = 2)
    private float angX;

    @Column(name = "angY", nullable = false, precision = 2)
    private float angY;

    @Column(name = "angZ", nullable = false, precision = 2)
    private float angZ;

    @Column(name = "angW", nullable = false, precision = 2)
    private float angW;

    public int getDesID() {
        return this.id;
    }

    public void setCarID(int desID) {
        this.id = desID;
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

    public void setDesID(int desID) {
        this.id = desID;
    }

    public float getAngX() {
        return angX;
    }

    public void setAngX(float angX) {
        this.angX = angX;
    }

    public float getAngY() {
        return angY;
    }

    public void setAngY(float angY) {
        this.angY = angY;
    }

    public float getAngZ() {
        return angZ;
    }

    public void setAngZ(float angZ) {
        this.angZ = angZ;
    }

    public float getAngW() {
        return angW;
    }

    public void setAngW(float angW) {
        this.angW = angW;
    }

    public String getLoc(){
        return String.format("%f %f %f %f %f %f %f",locX,locY,locZ,angX,angY,angZ,angW);
    }
}
