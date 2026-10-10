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
    
    void enqueue(Node input, int parentId) {
        
        Node[] nodeArray = {input, Astar.grid[parentId / Astar.grid[0].length][parentId % Astar.grid[0].length]};
        
        Link l = new Link(nodeArray, null);

        if (head == null || input.finalCost.get(parentId) < head.value[0].finalCost.get(parentId)) {
            l.next = head;
            head = l;
            if (end == null) {
                end = l;
            }
            return;
        }
        
        Link current = head;
        
        while (current.next != null && current.next.value[0].finalCost.get(parentId) <= input.finalCost.get(parentId)) {
            current = current.next;
        }
        l.next = current.next;
        current.next = l;
        if (l.next == null) {
            end = l;
        }
    }
        
    Node[] dequeue() {
        if (isEmpty()) {
            throw new NullPointerException("Queue is empty");
        } else {
            Node[] output = head.value;
            head = head.next;
            if (head == null) {
                end = null;
            }
            return output;
        }
    }
}
