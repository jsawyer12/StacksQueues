package impl;

import common.QueueEmptyException;
import common.QueueFullException;
import interfaces.IPriorityQueue;


public class PriorityQueue implements IPriorityQueue {

    private Comparable[] priorityQueue;

    public PriorityQueue(int size) {
        priorityQueue = new Comparable[size];
    }

    public Comparable[] getPriorityQueue() {
        return this.priorityQueue;
    }

    @Override
    public void enqueue(Comparable element) throws QueueFullException {
        int size = size();
        if (size == priorityQueue.length) {
            throw new QueueFullException();
        }
        else {
            for (int i = 0; i < priorityQueue.length; i++) {
                if (priorityQueue[i] == null) {
                    priorityQueue[size] = element;
                    break;
                }
            }
        }
     }

    @Override
    public Comparable dequeue() throws QueueEmptyException {
        if (size() == 0) {
            throw new QueueEmptyException();
        }
        else {
            int topIndex = 0;
            Comparable tempElement = priorityQueue[0];
            if (size() > 0) {
                for (int i = 1; i < size(); i++) {
                    if (priorityQueue[i].compareTo(tempElement) > 0) {
                        tempElement = priorityQueue[i];
                        topIndex = i;
                    }
                }
                priorityQueue[topIndex] = null;
            }
            return tempElement;
        }
    }

    @Override
    public int size() {
        int size = 0;
        for (int i = 0; i < priorityQueue.length; i++) {
            if (priorityQueue[i] != null) {
                size++;
            }
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < priorityQueue.length; i++) {
            if (priorityQueue[i] != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void clear() {
        priorityQueue = new Comparable[priorityQueue.length];
    }
}
