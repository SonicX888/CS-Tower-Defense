package com.mycompany.cs_tower_defense;

/**
 *
 * @author kyllianlim
 */
public class PriorityQueue {

    Link head = null;
    Link end = null;

    boolean isEmpty() {
        return head == null;
    }

    void enqueue(Node input, double priority) {

        Link l = new Link(input, priority, null);

        if (head == null || priority < head.priority) {
            l.next = head;
            head = l;
            if (end == null) {
                end = l;
            }
            return;
        }

        Link current = head;

        while (current.next != null && current.next.priority <= priority) {
            current = current.next;
        }
        l.next = current.next;
        current.next = l;
        if (l.next == null) {
            end = l;
        }
    }

    Node dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        } else {
            Node output = head.value;
            head = head.next;
            if (head == null) {
                end = null;
            }
            return output;
        }
    }
}
