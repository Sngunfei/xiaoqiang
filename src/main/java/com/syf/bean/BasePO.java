package com.syf.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class BasePO implements Serializable{

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }

}
