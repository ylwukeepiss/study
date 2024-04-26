package com.garden.alanni.algor.LinkedList;

/**
 * @author 吴宇伦
 */
public class LinkedListNode {
    private int val;
    LinkedListNode next;

    public LinkedListNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public LinkedListNode getNext() {
        return next;
    }

    public void setNext(LinkedListNode next) {
        this.next = next;
    }
}
