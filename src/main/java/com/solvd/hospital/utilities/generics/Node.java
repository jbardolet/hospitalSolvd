package com.solvd.hospital.utilities.generics;


public class Node <T> {
    private T data;
    private Node<T> next;

    Node(T d){
        this.data = d;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
