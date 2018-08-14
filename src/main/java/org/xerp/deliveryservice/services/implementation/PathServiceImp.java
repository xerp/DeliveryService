package org.xerp.deliveryservice.services.implementation;

import org.springframework.stereotype.Service;
import org.xerp.deliveryservice.dto.*;
import org.xerp.deliveryservice.services.PathService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PathServiceImp extends AbstractService implements PathService {

    private <T extends Node> Node getNode(List<T> sourceList, String name) {
        return sourceList
                .stream()
                .filter(n -> n.getName().equalsIgnoreCase(name))
                .findFirst()
                .get();
    }

    private String getShortestPath(Graph graph) {
        var source = graph.getSource();
        var path = new LinkedList<String>();

        while (source != null) {
            for (var adjacentNodePair : source.getAdjacentNodes().entrySet()) {

            }
            if (!source.getAdjacentNodes().isEmpty()) {
                source = source
                        .getAdjacentNodes()
                        .entrySet()
                        .stream()
                        .min(Comparator.comparingDouble(Map.Entry::getValue))
                        .get()
                        .getKey();
            } else {
                source = null;
            }
        }

        return String.join("-", path);
    }

    private Graph getCalculatedGraph(Route route) {
        Set<Node> readyNodes = new HashSet<>();
        Set<Node> notReadyNodes = new HashSet<>();

        var graph = getGraphFromOrigin(route.getPaths(), route.getOrigin());
        graph.getSource().setDistance(0d);

        notReadyNodes.add(graph.getSource());

        while (!notReadyNodes.isEmpty()) {
            var currentNode = getLowestDistanceNode(notReadyNodes);
            notReadyNodes.remove(currentNode);
            for (var nodeDistancePair : currentNode.getAdjacentNodes().entrySet()) {
                var adjacentNode = nodeDistancePair.getKey();

                if (!readyNodes.contains(adjacentNode)) {
                    setMinimumDistance(adjacentNode, nodeDistancePair.getValue(), currentNode);
                    notReadyNodes.add(adjacentNode);
                }
            }

            readyNodes.add(currentNode);
        }

        return graph;
    }

    private Graph getGraphFromOrigin(Paths paths, Point origin) {

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

            originNode.addDestination(destinationNode, path.getTime() + path.getCost());
        });

        var graph = new Graph(source);
        graph.addNodes(nodes);

        return graph;
    }

    private Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        var lowestDistance = Double.MAX_VALUE;

        for (var node : unsettledNodes) {
            var nodeDistance = node.getDistance();

            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }

        return lowestDistanceNode;
    }

    private void setMinimumDistance(Node node, Double edgeWeigh, Node sourceNode) {
        var sourceDistance = sourceNode.getDistance();
        var nodeDistance = sourceDistance + edgeWeigh;

        if (nodeDistance < node.getDistance()) {
            node.setDistance(nodeDistance);
        }
    }

    @Override
    public Path newPath(Point origin, Point destination, double time, double cost) {
        return new Path(origin, destination, time, cost);
    }

    @Override
    public Path newPath(Map<String, Optional<Point>> pointsMap, String origin, String destination, double time, double cost) {
        if (!pointsMap.get(origin).isPresent() && pointsMap.get(destination).isPresent()) {
            return null;
        }

        var pointOrigin = pointsMap.get(origin).get();
        var pointDestination = pointsMap.get(destination).get();

        return new Path(pointOrigin, pointDestination, time, cost);
    }

    @Override
    public void setBestPaths(Route route) {
        var graph = getCalculatedGraph(route);
        var path = getShortestPath(graph);

        route.setShortestPath(path);
    }
}
