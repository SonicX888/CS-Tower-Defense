package com.mycompany.cs_tower_defense.path;

/**
 * Class of a link of the priority queue
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public class Link {

    // node stored in the link
    Node value;
    // priority of the node (the lowest is taken out first)
    double priority;
    // next link of the queue
    Link next;

    /**
     * Creates a link with a node, its priority and the next link.
     *
     * @param value node stored in the link
     * @param priority priority of the node (the lowest is taken out first)
     * @param next next link of the queue
     */
    public Link(Node value, double priority, Link next) {

        this.value = value;
        this.priority = priority;
        this.next = next;

    }

}
