package com.mycompany.tue_tds;

/**
 *
 * @author kyllianlim
 */
public class Node {
    int x;
    int y;
    boolean isTower;
    int[] parent;
    float finalValue;
    
    public Node(int x, int y, boolean isTower, int[] parent, float finalValue) {
        this.x = x;
        this.y = y;
        this.isTower = isTower;
        this.parent = parent;
        this.finalValue = finalValue;
    }
}
