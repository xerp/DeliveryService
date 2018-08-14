package org.xerp.deliveryservice.dto;

import java.io.Serializable;
import java.util.List;

public class Paths implements Serializable {
    private List<Path> paths;
    private Long counts;

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }

    public Long getCounts() {
        return counts;
    }

    public void setCounts(Long counts) {
        this.counts = counts;
    }
}
