package org.xerp.deliveryservice.services.implementation.paths;

import org.springframework.stereotype.Component;
import org.xerp.deliveryservice.dto.Graph;
import org.xerp.deliveryservice.dto.Node;
import org.xerp.deliveryservice.dto.Route;

import java.util.*;

@Component
public class DijkstraStrategy extends AbstractStrategy {

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

    private String getShortestPath(Graph graph) {
        var source = graph.getSource();
        var path = new LinkedList<String>();

        path.add(source.getName());
        while (source != null) {
            if (!source.getAdjacentNodes().isEmpty()) {
                source = source
                        .getAdjacentNodes()
                        .entrySet()
                        .stream()
                        .min(Comparator.comparingDouble(Map.Entry::getValue))
                        .get()
                        .getKey();
                path.add(source.getName());
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

    @Override
    public String findShortestPath(Route route) {
        var graph = getCalculatedGraph(route);
        return getShortestPath(graph);
    }
}
