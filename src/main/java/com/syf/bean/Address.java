package com.syf.bean;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "place", schema = "xq")
public class Address extends BasePO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "address", nullable = false, length = 512)
    private String address;

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

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getLocationInfo(){
        return String.format("%f,%f,%f,%f,%f,%f,%f", locX, locY, locZ, angX, angY, angZ, angW);
    }
}
