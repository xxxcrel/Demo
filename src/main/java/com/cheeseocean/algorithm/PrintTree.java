package com.cheeseocean.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.ArrayStack;

public class PrintTree {
    static class Node{
        int id;
        int parentId;
        String name;
        public Node(int id, int parentId, String name){
            this.id = id;
            this.parentId = parentId;
            this.name = name;
        }
    }

    static class Tuple{
        int i;
        Node node;
        public Tuple(int i, Node node){
            this.i = i;
            this.node = node;
        }
    }
    public static void main(String[] args) {
        List<Node> nodeList = Arrays.asList(
                new Node(1, 0, "AA"),
                new Node(2, 1, "BB"),
                new Node(3,1, "CC"),
                new Node(4, 2, "DD"),
                new Node(5, 2, "EE"),
                new Node(6, 3, "FF"),
                new Node(7, 3, "GG")
        );
        List<Node> printed = new ArrayList<>();
        List<Tuple> stack = new ArrayList<>();
        stack.add(new Tuple(-1, new Node(0, 0, "root")));
        while(!stack.isEmpty()){
            Tuple found = findTuple(stack, nodeList, printed);
            if (found != null) {
                printed.add(found.node);
                System.out.println(calc(found.i) + found.node.name);
            }

        }
    }
    static String calc(int max){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < max; ++i){
            sb.append("  ");
        }
        return sb.toString();
    }

    public static Tuple findTuple(List<Tuple> stack, List<Node> nodeList, List<Node> printed){
        boolean found = false;
        Tuple foundTuple = null;
        int willFound = stack.get(stack.size() - 1).node.id;
        for (Node n : nodeList){
            if(n.parentId == willFound && !printed.contains(n)){
//                System.out.println("found");
                foundTuple = new Tuple(stack.get(stack.size() - 1).i + 1, n);
                stack.add(foundTuple);
                found = true;
                break;
            }
        }
        if (!found) {
            stack.remove(stack.size() - 1);
        }
        return foundTuple;
    }
}
