package com.mycompany.tue_tds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author kyllianlim
 */
public class Astar {

    static Node[][] grid = new Node[9][11];
    static double cost = 64;

    static void gridCreation() {
        int id = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int x = j * 64;
                int y = (i + 1) * 64;
                grid[i][j] = new Node(
                        id,
                        x,
                        y,
                        false
                );
                id += 1;
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                ArrayList<Node> neighbors = new ArrayList<Node>();
                if (j + 1 < 11) {
                    neighbors.add(grid[i][j + 1]);
                }
                if (i - 1 >= 0) {
                    neighbors.add(grid[i - 1][j]);
                }
                if (i + 1 < 9) {
                    neighbors.add(grid[i + 1][j]);
                }
                if (j - 1 >= 0) {
                    neighbors.add(grid[i][j - 1]);
                }
                grid[i][j].neighbors = neighbors;
            }
        }
    }

    static double heuristic(int xA, int yA, int xB, int yB) {
        return Math.sqrt(
                Math.pow((xB - xA), 2)
                + Math.pow((yB - yA), 2)
        );
    }

    static ArrayList<Node> reconstructPath(
            HashMap<Node, Node> cameFrom,
            Node current
    ) {
        ArrayList<Node> path = new ArrayList<>();

        while (current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }

        Collections.reverse(path);
        return path;
    }

    static ArrayList<Node> findPath(Node start) {
        Node goal = grid[4][10];
        HashMap<Node, Double> g = new HashMap<>();
        HashMap<Node, Node> cameFrom = new HashMap<>();
        HashSet<Node> closed = new HashSet<>();
        PriorityQueue open = new PriorityQueue();

        g.put(start, 0.0);
        open.enqueue(start, start.heuristicCost);

        while (!open.isEmpty()) {

            Node current = open.dequeue();

            if (current == goal) {
                return reconstructPath(cameFrom, current);
            }

            if (closed.contains(current)) {
                continue;
            } else {
                closed.add(current);
            }

            for (Node n : current.neighbors) {
                if (n.isTower || closed.contains(n)) {
                    continue;
                }
                double tentative = g.get(current) + cost;
                double gScore = Double.POSITIVE_INFINITY;
                if (g.get(n) != null) {
                    gScore = g.get(n);
                }
                if (tentative < gScore) {
                    g.put(n, tentative);
                    cameFrom.put(n, current);
                    open.enqueue(n, tentative + n.heuristicCost);
                }
            }
        }
        return null;
    }
}
