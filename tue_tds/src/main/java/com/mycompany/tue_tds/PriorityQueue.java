package com.mycompany.tue_tds;

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
    
    void enqueue(Node input) {
        
        Link l = new Link(input, null);

        if (head == null || input.finalValue < head.value.finalValue) {
            l.next = head;
            head = l;
            if (end == null) {
                end = l;
            }
            return;
        }
        
        Link current = head;
        
        while (current.next != null && current.next.value.finalValue <= input.finalValue) {
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
            throw new NullPointerException("Queue is empty");
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
