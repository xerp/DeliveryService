package org.xerp.deliveryservice.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Paths implements Serializable {
    private List<Path> paths;
    private long count;


    public Paths() {
        this(new ArrayList<>());
    }

    public Paths(Path... paths) {
        this(Arrays.asList(paths));
    }

    public Paths(List<Path> paths) {
        this.paths = paths;
        this.count = this.paths.size();
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
