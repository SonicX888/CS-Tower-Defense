package com.mycompany.tue_tds;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author kyllianlim
 */
public class Node {
    int id;
    int x;
    int y;
    double heuristicCost;
    HashMap<Integer, Double> finalCost = new HashMap<>();
    ArrayList<Node> neighbors;
    boolean isTower;
    
    public Node(
            int id, 
            int x, 
            int y, 
            double heuristicCost, 
            boolean isTower
    ) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.heuristicCost = heuristicCost;
        this.isTower = isTower;
    }
}
