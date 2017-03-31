package impl;

import common.StackEmptyException;
import common.StackOverflowException;
import interfaces.IStack;

public class Stack implements IStack {

    private Object[] stack;
    private int bottomIndex;

    public Stack(int size) {
        stack = new Object[size];
        this.bottomIndex = -1;
    }

    public Object[] getStack() {
        return this.stack;
    }

    /**
     * returns the index of the array for the bottom element in the stack
     * @return
     */
    public int findBottom() {
        bottomIndex = -1;
        for (int i = 0; i < stack.length; i++) {
            if (stack[i] != null) {
                bottomIndex = i;
            }
        }
        return bottomIndex;
    }

    /**
     * if bottom of stack is not null, stack is full and overflow exception
     * is thrown. else, element is pushed onto top of stack
     * @param element the element to be pushed
     * @throws StackOverflowException
     */
    @Override
    public void push(Object element) throws StackOverflowException {
        if (findBottom() >= stack.length-1) {
            throw new StackOverflowException();
        }
        else {
            for (int i = (bottomIndex+1); i > 0; i--) {
                stack[i] = stack[i-1];
            }
            stack[0] = element;
            bottomIndex++;
        }
    }

    @Override
    public Object pop() throws StackEmptyException {
        if (findBottom() < 0) {
            throw new StackEmptyException();
        }
        else {
            Object element = stack[0];
            for (int i = 0; i <= bottomIndex; i++) {
                stack[i] = stack[i+1];
            }
            bottomIndex--;
            return element;
        }
    }

    @Override
    public Object top() throws StackEmptyException {
        if (isEmpty()) {
            throw new StackEmptyException();
        }
        else {
            return stack[0];
        }
    }

    @Override
    public int size() {
        return (findBottom() + 1);
    }

    @Override
    public boolean isEmpty() {
        if (findBottom() == -1) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void clear() {
        stack = new Object[stack.length];
    }
}
