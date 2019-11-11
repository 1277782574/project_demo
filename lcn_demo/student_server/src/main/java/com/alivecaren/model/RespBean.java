package com.alivecaren.model;

import lombok.Data;

import java.io.Serializable;

@Data
public  class RespBean implements Serializable {

    private int status;
    private String msg;
    private Object obj;

}
