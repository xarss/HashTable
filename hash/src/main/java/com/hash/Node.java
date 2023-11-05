package com.hash;

// REGISTRO
public class Node {
    private int value;
    private Node next;

    public Node() {
        this.value = -1;
        this.next  = null;
    }

    public Node(int value) {
        this.value = value;
        this.next  = null;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
