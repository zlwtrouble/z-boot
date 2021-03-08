package com.zhaolw.zoo.boot.controller;

import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class LinkList {

    public Node head;

    public void createLinkList(int[] x) {   //创建一个链表
        if (x == null) {
            return;
        }
        Node temp = new Node();
        Node next;
        head = temp;
        for (int i = 0; i < x.length; i++) {
            temp.setData(x[i]);
            next = new Node();
            temp.setNext(next);

            temp = next;
        }
    }

    public void displayLinkList() {  //正序输出链表的所有内容
        Node node = head;
        do {
            System.out.print(node.getData() + "--->");
            node = node.getNext();
        } while (node != null);
    }

    public void reverseLinkList() {  //逆序输出链表的所有内容
        if (head == null || head.getNext() == null) {  //当链表只有一个头节点或者只有一个结点，逆序还是原来的链表，所以直接返回
            return;
        } else {
            Node p = head.getNext();
            Node q = head.getNext().getNext();
            p.setNext(null);//将第一个结点的next置为空，否则会出现一个环
            Node temp = null;
            while (q != null) {
                temp = q.getNext();
                q.setNext(p);
                p = q;
                q = temp;
            }
            if (q == null) {
                head.setNext(p);
                q = null;
            }
        }
    }

//    public static void main(String[] args) {
//        LinkList linkList = new LinkList();
//        int[] x = new int[5];
//        x[0] = 1;
//        x[1] = 2;
//        x[2] = 3;
//        x[3] = 4;
//        x[4] = 5;
//        linkList.createLinkList(x);
//        linkList.displayLinkList();
//        linkList.reverseLinkList();
//        linkList.displayLinkList();
//    }


    public static void main(String[] args) throws Exception {
        BlockingQueue<Integer> queue = new
                SynchronousQueue<>();
        queue.put(1);
        // System. out .print();
//        System. out .print(queue.offer(2) + " ");
//        System. out .print(queue.offer(3) + " ");
        System.out.print(queue.take() + " ");
        System.out.println(queue.size());
    }


    public void reverseLinkListStack() {  //借助栈来实现逆序输出
        Stack<Node> stack = new Stack<Node>();
        Node node = head.getNext();
        while (node != null) {
            stack.push(node);
            node = node.getNext();
        }
        while (stack.size() > 0) {
            node = stack.pop();
            System.out.print(node.getData() + "--->");
        }
        System.out.println("END");
    }
}
