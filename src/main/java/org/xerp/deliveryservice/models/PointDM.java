package org.xerp.deliveryservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class PointDM {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    public PointDM() {
    }

    public PointDM(String name) {
        this.name = name.toUpperCase();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name.toUpperCase();
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        var point = (PointDM) o;
        return Objects.equals(getName(), point.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
