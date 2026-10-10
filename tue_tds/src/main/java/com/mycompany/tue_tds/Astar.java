package com.mycompany.tue_tds;

import java.util.Collections;
import java.util.ArrayList;


/**
 *
 * @author kyllianlim
 */
public class Astar {
    
    static Node[][] grid = new Node[9][11];
    static PriorityQueue astarQueue = new PriorityQueue();
    static double cost = 64;
    static double pastCost = 0;
    static double finalCost = 0;
    
    static void gridCreation() {
        int id = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int x = j * 64;
                int y = (i+1) * 64;
                grid[i][j] = new Node(
                        id, 
                        x, 
                        y, 
                        Astar.heuristic(x, y, 704, 384), 
                        false
                );
                id += 1;
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                ArrayList<Node> neighbors = new ArrayList<Node>();
                if (j + 1 < 11) {
                    neighbors.add(grid[i][j+1]);
                }
                if (i - 1 >= 0) {
                    neighbors.add(grid[i-1][j]);
                }
                if (i + 1 < 9) {
                    neighbors.add(grid[i+1][j]);
                }
                if (j - 1 >= 0) {
                    neighbors.add(grid[i][j-1]);
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
    
    static ArrayList<Node> reconstructPath(ArrayList<Node[]> explored) {
        ArrayList<Node> result = new ArrayList<Node>();
        Node current = explored.get(explored.size() - 1)[0];
        Node parent = explored.get(explored.size() - 1)[1];
        result.add(current);
        for (int i = explored.size() - 2; i >= 0; i--) {
            if (explored.get(i)[0].id == parent.id) {
                current = explored.get(i)[0];
                parent = explored.get(i)[1];
                result.add(current);
                continue;
            }
        }
        Collections.reverse(result);
        return result;
    }
    
    static ArrayList<Node> findPath(Node start) {
        ArrayList<Node[]> explored = new ArrayList<Node[]>();
        Node parent = null;
        Node[] newStart = {start};
        while (!astarQueue.isEmpty()) {
            if (newStart[0].id == 54) {
                return reconstructPath(explored);
            }
            for (int i = 0; i < newStart[0].neighbors.size(); i++) {
                if (!newStart[0].neighbors.get(i).isTower) {
                    finalCost = newStart[0].neighbors.get(i).heuristicCost + pastCost + cost;
                }
                if (parent == null) {
                    newStart[0].neighbors.get(i).finalCost.put(start.id, finalCost);
                    astarQueue.enqueue(newStart[0].neighbors.get(i), start.id);
                } else {
                    newStart[0].neighbors.get(i).finalCost.put(parent.id, finalCost);
                    astarQueue.enqueue(newStart[0].neighbors.get(i), parent.id);
                }
            }
            parent = newStart[0];
            newStart = astarQueue.dequeue();
            pastCost = newStart[0].finalCost.get(parent.id);
            explored.add(newStart);
        }
        return null;
    }
}
