package com.mycompany.cs_tower_defense;

/**
 *
 * @author kyllianlim
 */
public class Link {

    Node value;
    double priority;
    Link next;

    public Link(Node value, double priority, Link next) {

        this.value = value;
        this.priority = priority;
        this.next = next;

    }

}
