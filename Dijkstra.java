package com.sai.designPatterns.shortestPath;

import com.sai.designPatterns.Edge;

import java.util.*;

public class Dijkstra {
    public static void dijkstra(List<List<Edge>> graph, int source, int[] distances) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(source, source, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int u = current.getDest();
            int dist = current.getWeight();

            for (Edge edge : graph.get(u)) {
                int v = edge.getDest();
                int weight = edge.getWeight();
                if (dist + weight < distances[v]) {
                    distances[v] = dist + weight;
                    pq.add(new Edge(u, v, distances[v]));
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 7, 8));
        edges.add(new Edge(1, 2, 8));
        edges.add(new Edge(1, 7, 11));
        edges.add(new Edge(2, 3, 7));
        edges.add(new Edge(2, 5, 4));
        edges.add(new Edge(2, 8, 2));
        edges.add(new Edge(3, 4, 9));
        edges.add(new Edge(3, 5, 14));
        edges.add(new Edge(4, 5, 10));
        edges.add(new Edge(5, 6, 2));
        edges.add(new Edge(6, 7, 1));
        edges.add(new Edge(6, 8, 6));
        edges.add(new Edge(7, 8, 7));

        int n = 9; // Number of vertices
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Populate the graph with edges
        for (Edge edge : edges) {
            graph.get(edge.getSrc()).add(edge);
        }

        // Array to store distances from source vertex
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[0] = 0; // Distance from source to source is 0

        // Run Dijkstra's algorithm
        dijkstra(graph, 0, distances);

        // Print shortest distances from source vertex
        System.out.println("Shortest distances from source vertex:");
        for (int i = 0; i < n; i++) {
            System.out.println("Vertex " + i + ": " + distances[i]);
        }
    }
}

//        Vertex 0: 0
//        Vertex 1: 4
//        Vertex 2: 12
//        Vertex 3: 19
//        Vertex 4: 28
//        Vertex 5: 16
//        Vertex 6: 18
//        Vertex 7: 8
//        Vertex 8: 14
