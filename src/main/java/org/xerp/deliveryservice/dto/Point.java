package org.xerp.deliveryservice.dto;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        var point = (Point) obj;
        return Objects.equals(getName(), point.getName());
    }
}
