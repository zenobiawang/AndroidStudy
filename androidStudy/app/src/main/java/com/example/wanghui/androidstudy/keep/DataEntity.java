package com.example.wanghui.androidstudy.keep;

import java.io.Serializable;

/**
 * Created by wanghui on 2016/4/21.
 */
public class DataEntity implements Serializable{
    public static final long serialVersionUID = 111111111111111l;
    public String name;
    public String value;

    public DataEntity(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
