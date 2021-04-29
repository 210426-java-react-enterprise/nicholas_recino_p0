package com.revature.fsmapp.util.collection;

public class Node {
    private Node nextNode;
    private Node prevNode;
    private Object contents;

    public Node(Node nextNode, Node prevNode, Object contents) {
        this.nextNode = nextNode;
        this.prevNode = prevNode;
        this.contents = contents;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public Node getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(Node prevNode) {
        this.prevNode = prevNode;
    }

    public Object getContents() {
        return contents;
    }

    public void setContents(Object contents) {
        this.contents = contents;
    }

}
