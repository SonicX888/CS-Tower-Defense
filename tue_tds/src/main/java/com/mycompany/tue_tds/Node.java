package com.mycompany.tue_tds;

/**
 *
 * @author kyllianlim
 */
public class Node {
    int id;
    int parentId;
    int x;
    int y;
    float heuristicCost;
    float pastCost;
    float finalCost;
    boolean isTower;
    
    public Node(
            int id, 
            int parentId, 
            int x, 
            int y, 
            float heuristicCost, 
            float pastCost, 
            boolean isTower
    ) {
        this.id = id;
        this.parentId = parentId;
        this.x = x;
        this.y = y;
        this.heuristicCost = heuristicCost;
        this.pastCost = pastCost;
        this.isTower = isTower;
        this.finalCost = pastCost + heuristicCost;
    }
}
