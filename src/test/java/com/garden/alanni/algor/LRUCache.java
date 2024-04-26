package com.garden.alanni.algor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 吴宇伦
 */
public class LRUCache {
//    class Node {
//        int key;
//        int val;
//        Node l, r;
//        public Node(int key, int val) {
//            this.key = key;
//            this.val = val;
//        }
//    }
//    int cap;
//    Node head, tail;
//    Map<Integer, Node> nodeMap;
//    public LRUCache(int capacity) {
//        this.cap = capacity;
//        nodeMap = new HashMap<>();
//        head = new Node(-1, -1);
//        tail = new Node(-1, -1);
//        head.r = tail;
//        tail.l = head;
//    }
//
//    public int get(int key) {
//        if (nodeMap.containsKey(key)) {
//            Node node = nodeMap.get(key);
//            refresh(node);
//            return node.val;
//        }
//        return -1;
//    }
//
//    public void put(int key, int value) {
//        Node node = null;
//        if (nodeMap.containsKey(key)) {
//            node = nodeMap.get(key);
//            node.val = value;
//        } else {
//            if (nodeMap.size() == cap) {
//                Node lastNode = tail.l;
//                nodeMap.remove(lastNode.key);
//                delete(lastNode);
//            }
//            node = new Node(key, value);
//            nodeMap.put(key, node);
//        }
//        refresh(node);
//    }
//
//    public void delete(Node node) {
//        if (node.l != null) {
//            Node left = node.l;
//            left.r = node.r;
//            node.r.l = left;
//        }
//    }
//
//    public void refresh(Node node) {
//        delete(node);
//        node.r = head.r;
//        node.l = head;
//        head.r.l = node;
//        head.r = node;
//    }

    class Node {
        int key, val;
        Node l, r;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    int capacity;
    Map<Integer, Node> valMap;
    Node head, tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        valMap = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.r = tail;
        tail.l = head;
    }

    public int get(int key) {
        if (valMap.containsKey(key)) {
            Node node = valMap.get(key);
            refresh(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node node = null;
        if (valMap.containsKey(key)) {
            node = valMap.get(key);
            node.val = value;
        } else {
            if (valMap.size() == capacity) {
                Node del = tail.l;
                delete(del);
                valMap.remove(del.key);
            }
            node = new Node(key, value);
            valMap.put(key, node);
        }
        refresh(node);
    }

    private void refresh(Node node) {
        delete(node);
        node.l = head;
        node.r = head.r;
        head.r.l = node;
        head.r = node;
    }

    public void delete(Node node) {
        Node left = node.l;
        if (left != null) {
            left.r = node.r;
            node.r.l = left;
            node.l = null;
            node.r = null;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        int i = lruCache.get(1);
        lruCache.put(3, 3);
        int i1 = lruCache.get(2);
        lruCache.put(4, 4);
        int i2 = lruCache.get(1);
        int i3 = lruCache.get(3);
        int i4 = lruCache.get(4);
        System.out.println("here");
    }
}
