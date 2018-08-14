package org.xerp.deliveryservice.services.implementation.paths;

import org.xerp.deliveryservice.dto.Graph;
import org.xerp.deliveryservice.dto.Node;
import org.xerp.deliveryservice.dto.Paths;
import org.xerp.deliveryservice.dto.Point;
import org.xerp.deliveryservice.services.ShortPathStrategy;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractStrategy implements ShortPathStrategy {

    private <T extends Node> Node getNode(List<T> sourceList, String name) {
        return sourceList
                .stream()
                .filter(n -> n.getName().equalsIgnoreCase(name))
                .findFirst()
                .get();
    }

    protected Graph getGraphFromOrigin(Paths paths, Point origin) {

        var nodes = paths
                .getPoints()
                .stream()
                .map(p -> new Node(p.getName()))
                .collect(Collectors.toList());

        var source = getNode(nodes, origin.getName());

        paths.getPaths().forEach(path ->
        {
            var originNode = getNode(nodes, path.getOrigin().getName());
            var destinationNode = getNode(nodes, path.getDestination().getName());

            originNode.addDestination(destinationNode, path.getTime());
        });

        var graph = new Graph(source);
        graph.addNodes(nodes);

        return graph;
    }

}
