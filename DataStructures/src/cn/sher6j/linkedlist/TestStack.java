package cn.sher6j.linkedlist;

import java.util.Stack;

/**
 * @author sher6j
 * @create 2020-05-02-21:53
 */
public class TestStack {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        //入栈
        stack.add("a");
        stack.add("b");
        stack.add("c");

        //出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}
