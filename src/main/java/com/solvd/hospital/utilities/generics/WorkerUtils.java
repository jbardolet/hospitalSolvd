package com.solvd.hospital.utilities.generics;

import com.solvd.hospital.entities.Worker;
import com.solvd.hospital.exceptions.LinkedListException;
import com.solvd.hospital.utilities.WorkShift;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class WorkerUtils<T extends Worker> implements Iterable<T> {
    private static final Logger logger = LogManager.getLogger("WorkerUtils");

    private MyLinkedList<Worker> array;

    public WorkerUtils(){

        array= new MyLinkedList<>();
    }
    public void add(T data){
        array.add(data);

    }
    public void set(T data, int index){
        array.add(index, data);
    }

    public T remove(int index) throws LinkedListException {
        T temp = (T) array.remove(index);
        if(temp == null){
            throw new LinkedListException("Index out of the list range");
        }
        return temp;

    }


    public void workerWorked(T worker) throws LinkedListException {

        int index = array.indexOf(worker);
        if(index == -1){
            throw new LinkedListException("This employee is not on the list");
        }
        array.add(array.remove(index));
    }

    public T firstWorker(WorkShift workShift){
        for(Worker wk: array){
            if((wk.getAvailability())&&(wk.getWorkShift()==workShift)){
                return (T) wk;
            }
        }
        return null;
    }
    public int size(){
        return this.array.getLength();
    }

    @Override
    public String toString() {
        String result = "List of "+array.getClass();
        for(Worker wk: array){
            result += wk.getName() + " "+ wk.getLastName();
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

    private class Iter implements Iterator<T>{
        private int indexI;

        public Iter(){
            indexI =0;
        }

        @Override
        public boolean hasNext() {
            return indexI < array.getLength();
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T data = (T) array.get(indexI);
            indexI ++;
            return data;
        }
    }
}
