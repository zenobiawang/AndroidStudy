package com.example.wanghui.androidstudy.keep;

import java.io.Serializable;

/**
 * Created by wanghui on 2016/4/21.
 */
public class DataEntity implements Serializable{
    public String name;
    private String value;

    public DataEntity(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
