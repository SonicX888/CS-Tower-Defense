package com.mycompany.cs_tower_defense;

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
            boolean isTower
    ) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.isTower = isTower;
        this.heuristicCost = Astar.heuristic(x, y, 640, 320);
    }

    public void setIsTower(boolean isTower) {
        this.isTower = isTower;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isIsTower() {
        return isTower;
    }
}

