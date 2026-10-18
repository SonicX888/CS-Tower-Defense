package com.mycompany.cs_tower_defense;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Class that finds the shortest path of the enemies towards the goal with the
 * A* algorithm
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public class Astar {

    // grid of 9 rows and 11 columns where each node of the map is stored
    public static Node[][] grid = new Node[9][11];
    // list where each node of the grid is stored
    public static ArrayList<Node> gridList = new ArrayList<Node>();
    // cost to move from a node to one of its neighbors (size of a cell)
    static double cost = 64;

    /**
     * Creates every node of the grid and links each node to its neighbors.
     */
    static void gridCreation() {
        // goes through each row of the grid
        for (int i = 0; i < grid.length; i++) {
            // goes through each column of the grid
            for (int j = 0; j < grid[0].length; j++) {
                // computes the position of the node on the window
                int x = j * 64;
                int y = (i + 1) * 64;
                // creates a node without a tower on it
                grid[i][j] = new Node(
                        x,
                        y,
                        false
                );
                // adds the node to the list
                gridList.add(grid[i][j]);
            }
        }
        // goes through each node again to find its neighbors
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                ArrayList<Node> neighbors = new ArrayList<Node>();
                // adds the right neighbor if it exists
                if (j + 1 < 11) {
                    neighbors.add(grid[i][j + 1]);
                }
                // adds the upper neighbor if it exists
                if (i - 1 >= 0) {
                    neighbors.add(grid[i - 1][j]);
                }
                // adds the lower neighbor if it exists
                if (i + 1 < 9) {
                    neighbors.add(grid[i + 1][j]);
                }
                // adds the left neighbor if it exists
                if (j - 1 >= 0) {
                    neighbors.add(grid[i][j - 1]);
                }
                // stores the neighbors in the node
                grid[i][j].neighbors = neighbors;
            }
        }
    }

    /**
     * Computes the distance between two points by adding the horizontal and
     * vertical distances (Manhattan distance).
     *
     * @param xA x position of the first point
     * @param yA y position of the first point
     * @param xB x position of the second point
     * @param yB y position of the second point
     * @return the distance between the two points
     */
    static double heuristic(int xA, int yA, int xB, int yB) {
        return Math.abs(xB - xA) + Math.abs(yB - yA);
    }

    /**
     * Rebuilds the path by going back from the goal to the start, then puts it
     * in the right order.
     *
     * @param cameFrom previous node of each node on the best path found
     * @param current node where the path ends (the goal)
     * @return the list of nodes going from the start to the goal
     */
    static ArrayList<Node> reconstructPath(
            HashMap<Node, Node> cameFrom,
            Node current
    ) {
        ArrayList<Node> path = new ArrayList<>();

        // adds each node by going back to the previous one until the start
        while (current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }

        // reverses the path so it goes from the start to the goal
        Collections.reverse(path);
        return path;
    }

    /**
     * Finds the shortest path from a node to the goal while avoiding the
     * towers. Returns null if there is no path.
     *
     * @param start node where the path begins (the node of the enemy)
     * @return the list of nodes going from the start to the goal, or null if
     * the goal can't be reached
     */
    static ArrayList<Node> findPath(Node start) {
        // goal of the enemies: cell in the middle of the last column
        Node goal = grid[4][10];
        // cost to reach each node from the start
        HashMap<Node, Double> g = new HashMap<>();
        // previous node of each node on the best path found
        HashMap<Node, Node> cameFrom = new HashMap<>();
        // nodes that have already been checked
        HashSet<Node> closed = new HashSet<>();
        // nodes that still have to be checked, sorted by priority
        PriorityQueue open = new PriorityQueue();

        // the start costs nothing to reach
        g.put(start, 0.0);
        open.enqueue(start, start.heuristicCost);

        // checks nodes as long as there are some left
        while (!open.isEmpty()) {

            // takes the node with the lowest priority
            Node current = open.dequeue();

            // returns the path if the goal is reached
            if (current == goal) {
                return reconstructPath(cameFrom, current);
            }

            // skips the node if it was already checked
            if (closed.contains(current)) {
                continue;
            } else {
                closed.add(current);
            }

            // goes through each neighbor of the node
            for (Node n : current.neighbors) {
                // skips the neighbor if it is a tower or was already checked
                if (n.isTower || closed.contains(n)) {
                    continue;
                }
                // cost to reach the neighbor through the current node
                double tentative = g.get(current) + cost;
                // puts the cost of the neighbor to infinity if it has none yet
                double gScore = Double.POSITIVE_INFINITY;
                if (g.get(n) != null) {
                    gScore = g.get(n);
                }
                // checks if this way to the neighbor is shorter
                if (tentative < gScore) {
                    // saves the new cost and the previous node of the neighbor
                    g.put(n, tentative);
                    cameFrom.put(n, current);
                    // adds the neighbor to the nodes to check
                    open.enqueue(n, tentative + n.heuristicCost);
                }
            }
        }
        // no path to the goal was found
        return null;
    }
}
