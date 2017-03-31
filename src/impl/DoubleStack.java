package impl;

import common.StackOverflowException;
import common.StacksTooBigException;
import interfaces.IDoubleStack;
import interfaces.IStack;

public class DoubleStack implements IDoubleStack {

    private Object[] doubleStack;
    private int firstBottomIndex;
    private boolean stacksInput;
    private Stack Stack1, Stack2;

    public DoubleStack(int maxSize) {
        doubleStack = new Object[maxSize];
        stacksInput = false;
    }

    public void insertStackValues(Stack Stack1, Stack Stack2) throws StacksTooBigException {
        if ((Stack1.size() + Stack2.size()) > doubleStack.length) {
            throw new StacksTooBigException();
        }
        else {
            this.Stack1 = Stack1;
            this.Stack2 = Stack2;
            firstBottomIndex = -1;
            for (int i = 0; i < Stack1.size(); i++) {
                doubleStack[i] = Stack1.getStack()[i];
                if (Stack1.getStack()[i] != null) {
                    firstBottomIndex = i;
                }
            }
            int doubleStackIndex = firstBottomIndex + 1;
            for (int i = 0; i < Stack2.size(); i++) {
                doubleStack[doubleStackIndex] = Stack2.getStack()[i];
                doubleStackIndex++;
            }
            stacksInput = true;
        }
    }

    public Object[] getDoubleStack() {
        return this.doubleStack;
    }

    @Override
    public IStack getFirstStack() {
        if (!stacksInput) {
            System.out.println("You haven't put stacks into the array yet");
            return null;
        }
        else {
            return Stack1;
        }
    }

    @Override
    public IStack getSecondStack() {
        if (!stacksInput) {
            System.out.println("You haven't put stacks into the array yet");
            return null;
        }
        else {
            return Stack2;
        }
    }
}
