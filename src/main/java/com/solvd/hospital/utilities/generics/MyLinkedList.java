package com.solvd.hospital.utilities.generics;


import com.solvd.hospital.exceptions.LinkedListException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList <T> implements Iterable<T>{
    private static final Logger logger = LogManager.getLogger("MyLinkedList");
    public Node<T> head;
    private static int length = 0;

    public MyLinkedList() {
        this.head = null;
    }

    //add objects to the linkedList
    public void add(T data){
        Node<T> nodeTemp = new Node(data);
        //if list is empty
        if(this.head==null){
            this.head = nodeTemp;
        }else{

            //temporal node to travers all the list
            Node<T> headTemp = head;
            while(headTemp.getNext()!=null){
                headTemp = headTemp.getNext();
            }
            headTemp.setNext(nodeTemp);

        }
        length++;
    }

    //add in specific position, first position is 1 (not 0).
    public void add(int position, T data){

        try{
            if (position > length + 1 || position<=0){
                throw new LinkedListException("Invalid position");

            }

            //insert the node in the first position
            if(position==1){
                //temp store the old head
                Node<T> temp = head;

                head = new Node<T>(data);

                head.setNext(temp);

            }else{
                Node<T> temp = head;
                Node<T> prev = new Node<T>(null);

                while(position -1 >0){
                    prev = temp;
                    temp = temp.getNext();
                    position--;
                }

                prev.setNext(new Node<>(data));
                prev.getNext().setNext(temp);


            }
            length++;


        }catch (LinkedListException ex){
            logger.error(ex.getMessage()+", it will no tbe inserted to the list");
        }



    }
    public T get(int position){
        Node<T> prev = new Node<>(null);
        Node<T> temp = head;
        int iter = 0;
        while(temp!=null&& iter <=position){
            if(iter==position){
                return temp.getData();
            }
            prev = temp;
            temp=temp.getNext();
            iter++;
        }
        return null;
    }

    //remove with return type generic
    public T remove(int position){
        Node<T> prev = new Node<>(null);


        //temporal node to travers all LinkedList
        Node<T> temp = head;
        //if the position is the first one
        if(temp !=null && position ==0){
            head = temp.getNext();
            length--;
            return temp.getData();
        }

        //if the position is greater than 0 but less than total lenght
        int iter= 0;
        while(temp!=null){
            if(iter==position){
                prev.setNext(temp.getNext());
                length--;
                return temp.getData();
            }else{
                prev= temp;
                temp = temp.getNext();
                iter++;
            }

        }
        //if the position is greater than length
        if(temp == null){
            return null;
        }

        return null;
    }

    //remove element from the list
    public void remove(T n){
        Node<T> prev = new Node<>(null);
        prev.setNext(head);
        Node<T> next = head.getNext();

        //temporal node to travers all LL
        Node<T> temp = head;

        boolean exists = false;

        //if it is the first node to be removed
        if (head.getData() == n) {
            head = head.getNext();

            // Node to be deleted exists
            exists = true;
        }

        // Iterating over LinkedList
        while (temp.getNext() != null) {
            if(temp.getData().equals(n)){
                prev.setNext(next);
                exists = true;

                //if the node is found, break the loop
                break;
            }
            prev = temp;
            temp = temp.getNext();
            next = temp.getNext();
        }

        if (exists == false && (temp.getData()).equals(n)) {
            prev.setNext(null);
            exists = true;
        }
        if (exists) {
            length--;
        }else {
            logger.info(
                    "Given Value is not present in linked list");
        }

    }

    public boolean isEmpty(){
        if(head==null){
            return true;
        }else{
            return false;
        }
    }

    public void clearList(){
        head = null;
        length = 0;
    }

    //return -1 if the data is not in the list
    public int indexOf(T data){
        Node<T> prev = new Node<>(null);
        //temporal node to travers all LinkedList
        Node<T> temp = head;
        int position=0;
        while(temp !=null){
            if(temp.getData()==data){
                return position;
            }
            prev = temp;
            temp = temp.getNext();
            position++;
        }
        return -1;
    }

    public int getLength(){
        return length;
    }

    @Override
    public String toString() {
        String S = "{ ";

        Node<T> iteratorHead = head;

        if (iteratorHead == null)
            return S + " }";

        while (iteratorHead.getNext() != null) {
            S += iteratorHead.getData() + " -> ";
            iteratorHead = iteratorHead.getNext();
        }

        S += String.valueOf(iteratorHead.getData());
        return S + " }";
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T>{
        private Node<T> current;

        public LinkedListIterator(){
            current=head;
        }

        @Override
        public boolean hasNext() {

            return current!=null;
        }

        @Override
        public T next() {
            if(current==null){
                throw new NoSuchElementException();
            }
            T temp = current.getData();
            current = current.getNext();
            return temp;
        }
    }
}

