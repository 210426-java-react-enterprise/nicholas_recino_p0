package com.revature.util;

public class LinkedList {
    private Node head;
    private Node tail;

    public boolean add(Object T){
        Node iterate = head;
        while(iterate.getNextNode()!=null){
            iterate.setNextNode(iterate.getNextNode());
        }
        iterate.setNextNode(new Node(null, iterate, T));
        tail = iterate.getNextNode();
        return true;
    }
}
