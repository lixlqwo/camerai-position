package com.pnoker.camera.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * @author pnoker
 */

public class Position implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    @JSONField(alternateNames = "aa")
    private int type;

    @JSONField(alternateNames = "center")
    private List<Double> position;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
