package org.xerp.deliveryservice.algorithms.implementation;

import org.xerp.deliveryservice.algorithms.ShortestPathAlgorithm;
import org.xerp.deliveryservice.dto.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractShortestPathAlgorithm implements ShortestPathAlgorithm {

    protected <T extends Node> Node getNode(Collection<T> nodes, String name) {
        return nodes
                .stream()
                .filter(n -> n.getName().equalsIgnoreCase(name))
                .findFirst()
                .get();
    }

    protected Graph getGraph(Paths paths, Point source) {
        var nodes = new HashSet<Node>();

        nodes.addAll(paths.getPaths()
                .stream()
                .map(p -> new Node(p.getOrigin().getName()))
                .collect(Collectors.toList()));

        nodes.addAll(paths.getPaths()
                .stream()
                .map(p -> new Node(p.getDestination().getName()))
                .collect(Collectors.toList()));


        paths.getPaths().forEach(path ->
        {
            var originNode = getNode(nodes, path.getOrigin().getName());
            var destinationNode = getNode(nodes, path.getDestination().getName());

            originNode.addDestination(destinationNode, getDestinationCost(path));
        });

        return new Graph(getNode(nodes, source.getName()), List.copyOf(nodes));

    }

    protected abstract int getDestinationCost(Path path);
}
