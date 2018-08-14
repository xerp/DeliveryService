package org.xerp.deliveryservice.dto;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Paths implements Serializable {
    private List<Path> paths;
    private long pathCount;

    private Set<Point> points;
    private long pointCount;


    public Paths() {
        this(new ArrayList<>());
    }

    public Paths(Path... paths) {
        this(Arrays.asList(paths));
    }

    public Paths(List<Path> paths) {
        this.paths = paths;
        this.pathCount = this.paths.size();

        this.points = new HashSet<>();

        points.addAll(paths
                .stream()
                .map(p -> p.getOrigin())
                .collect(Collectors.toSet()));

        points.addAll(paths
                .stream()
                .map(p -> p.getDestination())
                .collect(Collectors.toSet()));

        this.pointCount = this.points.size();
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }

    public long getPathCount() {
        return pathCount;
    }

    public void setPathCount(long pathCount) {
        this.pathCount = pathCount;
    }

    public Set<Point> getPoints() {
        return points;
    }

    public void setPoints(Set<Point> points) {
        this.points = points;
    }

    public long getPointCount() {
        return pointCount;
    }

    public void setPointCount(long pointCount) {
        this.pointCount = pointCount;
    }
}
