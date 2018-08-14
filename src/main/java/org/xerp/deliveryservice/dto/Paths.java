package org.xerp.deliveryservice.dto;

import java.io.Serializable;
import java.util.List;

public class Paths implements Serializable {
    private List<Path> paths;

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }
}
