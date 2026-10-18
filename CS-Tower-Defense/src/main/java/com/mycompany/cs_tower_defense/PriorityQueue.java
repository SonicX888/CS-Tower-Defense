package com.mycompany.cs_tower_defense;

/**
 * Class of a queue of nodes sorted from the lowest priority to the highest
 *
 * @author Kyllian LIM
 * @id 2463725
 * @author Ömer ÖZENLER
 * @id 2497778
 */
public class PriorityQueue {

    // first link of the queue
    Link head = null;
    // last link of the queue
    Link end = null;

    /**
     * Checks if the queue has no link.
     *
     * @return true if the queue is empty, false otherwise
     */
    boolean isEmpty() {
        return head == null;
    }

    /**
     * Adds a node to the queue at the right place depending on its priority.
     *
     * @param input node to add to the queue
     * @param priority priority of the node (the lowest is taken out first)
     */
    void enqueue(Node input, double priority) {

        // creates the link of the node
        Link l = new Link(input, priority, null);

        // checks if the queue is empty or if the node has the lowest priority
        if (head == null || priority < head.priority) {
            // puts the link at the front of the queue
            l.next = head;
            head = l;
            // the link is also the end if the queue was empty
            if (end == null) {
                end = l;
            }
            return;
        }

        // starts from the front of the queue
        Link current = head;

        // moves forward until the next link has a higher priority
        while (current.next != null && current.next.priority <= priority) {
            current = current.next;
        }
        // inserts the link after the current link
        l.next = current.next;
        current.next = l;
        // updates the end if the link is the last one
        if (l.next == null) {
            end = l;
        }
    }

    /**
     * Removes the node with the lowest priority from the queue and returns it.
     *
     * @return the node with the lowest priority
     */
    Node dequeue() {
        // throws an error if there is nothing to remove
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        } else {
            // takes the node at the front
            Node output = head.value;
            // removes the front link
            head = head.next;
            // empties the end if the queue is now empty
            if (head == null) {
                end = null;
            }
            return output;
        }
    }
}
