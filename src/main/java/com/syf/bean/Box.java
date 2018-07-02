package com.syf.bean;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "box", schema = "xq")
public class Box extends BasePO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boxID")
    private int boxID;

    @Column(name = "desID", nullable = false)
    private int desID;

    @Column(name = "x", nullable = false, precision = 1)
    private float x;

    @Column(name = "y", nullable = false, precision = 1)
    private float y;

    @Column(name = "z", nullable = false, precision = 1)
    private float z;

    @Column(name = "w", nullable = false, precision = 1)
    private float w;

    public Box() {}

    public int getBoxID() {
        return boxID;
    }

    public void setBoxID(int boxID) {
        this.boxID = boxID;
    }

    public int getDesID() {
        return desID;
    }

    public void setDesID(int desID) {
        this.desID = desID;
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

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }
}
