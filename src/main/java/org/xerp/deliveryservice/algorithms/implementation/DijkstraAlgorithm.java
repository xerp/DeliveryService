package org.xerp.deliveryservice.algorithms.implementation;

import org.springframework.stereotype.Component;
import org.xerp.deliveryservice.dto.Graph;
import org.xerp.deliveryservice.dto.Node;
import org.xerp.deliveryservice.dto.Paths;
import org.xerp.deliveryservice.dto.Point;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DijkstraAlgorithm extends AbstractShortestPathAlgorithm {


    private String getShortestPath(Graph graph, Point destinationPoint) {
        var destinationNode = getNode(graph.getNodes(), destinationPoint.getName());

        var path = destinationNode
                .getShortestPath()
                .stream()
                .sorted(Comparator.comparingInt(Node::getDistance))
                .map(Node::getName)
                .collect(Collectors.toList());

        path.add(destinationNode.getName());

        return String.join("-", path);
    }

    private Node getLowestDistanceNode(Set<Node> notVisitedNodes) {
        Node lowestDistanceNode = null;
        var lowestDistance = Integer.MAX_VALUE;

        for (var node : notVisitedNodes) {
            var nodeDistance = node.getDistance();

            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }

        return lowestDistanceNode;
    }

    private void setNodeMinimumDistance(Node node, Integer edgeWeigh, Node sourceNode) {
        var sourceDistance = sourceNode.getDistance();
        var nodeDistance = sourceDistance + edgeWeigh;

        if (nodeDistance < node.getDistance()) {
            node.setDistance(nodeDistance);

            var shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            node.setShortestPath(shortestPath);
        }
    }

    private Graph processGraph(Paths paths, Point source) {
        var visitedNodes = new HashSet<Node>();
        var notVisitedNodes = new HashSet<Node>();

        var graph = getGraph(paths, source);
        graph.getSource().setDistance(0);

        notVisitedNodes.add(graph.getSource());

        while (!notVisitedNodes.isEmpty()) {
            var currentNode = getLowestDistanceNode(notVisitedNodes);
            notVisitedNodes.remove(currentNode);

            for (var nodeDistancePair : currentNode.getAdjacentNodes().entrySet()) {
                var adjacentNode = nodeDistancePair.getKey();

                if (!visitedNodes.contains(adjacentNode)) {
                    setNodeMinimumDistance(adjacentNode, nodeDistancePair.getValue(), currentNode);
                    notVisitedNodes.add(adjacentNode);
                }
            }

            visitedNodes.add(currentNode);
        }

        return graph;
    }

    @Override
    public String findShortestPath(Point source, Point destination, Paths paths) {
        var graph = processGraph(paths, source);
        return getShortestPath(graph, destination);
    }
}
