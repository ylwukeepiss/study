package com.garden.alanni.algor.stack;

import java.util.Stack;

/**
 * @author 吴宇伦
 */
public class MinStack {
    public Stack<Node> stack;

    class Node {
        int val, min;

        public Node(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }
    public MinStack() {
        stack = new Stack<>();
        // 哨兵
        stack.push(new Node(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    public void push(int val) {
        Node node = null;
        if (val < stack.peek().min) {
            node = new Node(val, val);
        } else {
            node = new Node(val, stack.peek().min);
        }
        stack.push(node);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return stack.peek().min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        int min = minStack.getMin();//--> 返回 -3.
        minStack.pop();
        int top = minStack.top();// --> 返回 0.
        int min1 = minStack.getMin();//--> 返回 -2.
    }
}
