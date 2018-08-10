package org.xerp.deliveryservice.dto;

public class Point {

    private String name;

    public Point(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Point)) {
            return false;
        }

        var otherPoint = (Point) obj;

        return this.name == otherPoint.name;
    }
}
