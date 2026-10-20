package com.mycompany.cs_tower_defense.path;

import com.mycompany.cs_tower_defense.path.Astar;
import java.util.ArrayList;

/**
 * Class of a cell of the grid used by the A* algorithm
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public class Node {

    // position of the node on the window
    public int x;
    public int y;
    // estimated distance from the node to the goal
    double heuristicCost;
    // neighbors of the node (right, up, down and left)
    ArrayList<Node> neighbors;
    // checks if a tower is placed on the node
    boolean isTower;

    /**
     * Creates a node with its position and if a tower is on it.
     *
     * @param x x position of the node on the window
     * @param y y position of the node on the window
     * @param isTower true if a tower is placed on the node, false otherwise
     */
    public Node(
            int x,
            int y,
            boolean isTower
    ) {
        this.x = x;
        this.y = y;
        this.isTower = isTower;
        // computes the distance from the node to the goal
        this.heuristicCost = Astar.heuristic(x, y, Astar.GOAL_X, Astar.GOAL_Y);
    }

    /**
     * Sets if a tower is placed on the node.
     *
     * @param isTower true if a tower is placed on the node, false otherwise
     */
    public void setIsTower(boolean isTower) {
        this.isTower = isTower;
    }

    /**
     * Returns the x position of the node.
     *
     * @return the x position of the node on the window
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y position of the node.
     *
     * @return the y position of the node on the window
     */
    public int getY() {
        return y;
    }

    /**
     * Returns if a tower is placed on the node.
     *
     * @return true if a tower is placed on the node, false otherwise
     */
    public boolean isIsTower() {
        return isTower;
    }
}
