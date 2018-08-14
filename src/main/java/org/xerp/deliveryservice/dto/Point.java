package org.xerp.deliveryservice.dto;

import java.io.Serializable;

public class Point implements Serializable {
    private Long id;
    private String name;

    public Point() {

    }

    public Point(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
