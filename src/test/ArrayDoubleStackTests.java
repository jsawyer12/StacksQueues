package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import common.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import interfaces.IDoubleStack;
import impl.Stack;
import impl.DoubleStack;

/**
 * Tests array collection implementation.
 */
public class ArrayDoubleStackTests extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;

    private DoubleStack doubleStack = (DoubleStack) getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
    private Stack Stack1;
    private Stack Stack2;

    public ArrayDoubleStackTests() {
        Stack1 = new Stack(5);
        Stack2 = new Stack(5);
    }

    /**
     * Asserts that get___Stack methods are returning proper stacks and order
     * for their own stacks from the double stack
     * Also tests under circumstance that double stack isn't full
     */
    @Test
    public void doubleStackGetFirstStackTest() {
        for (int i = 0; i < 4; i++) {
            Object element = i;
            Object element2 = i + 5;
            try {
                Stack1.push(element);
                Stack2.push(element2);
            } catch (StackOverflowException e) {
                e.printStackTrace();
            }
        }
        try {
            doubleStack.insertStackValues(Stack1, Stack2);
        } catch (StacksTooBigException e) {
            e.printStackTrace();
        }
        for (int i = 3; i >= 0; i--) {
            assertEquals(Stack1.getStack()[i], doubleStack.getDoubleStack()[i]);
            assertEquals(Stack2.getStack()[i], doubleStack.getDoubleStack()[i+4]);
        }
        assertTrue(doubleStack.getFirstStack().equals(Stack1));
        assertTrue(doubleStack.getSecondStack().equals(Stack2));
    }

    /**
     * Tests to see if stacks are input into double stack properly
     * and that stacks are not interfering with each other's values
     */
    @Test
    public void doubleStackInputTest() {
        for (int i = 0; i < 5; i++) {
            Object element = i;
            try {
                Stack1.push(element);
                Stack2.push(element);
            } catch (StackOverflowException e) {
                e.printStackTrace();
            }
        }
        assertEquals(Stack1.size(), 5);
        assertEquals(Stack1.size(), Stack2.size());
        assertEquals(Stack1.size() + Stack2.size(), doubleStack.getDoubleStack().length);
        try {
            doubleStack.insertStackValues(Stack1, Stack2);
        } catch (StacksTooBigException e) {
            e.printStackTrace();
        }
        int index = 4;
        int secondIndex = 9;
        for (int i = 0; i < 5; i++) {
            assertEquals(i, doubleStack.getDoubleStack()[index]);
            assertEquals(i, doubleStack.getDoubleStack()[secondIndex]);
            index--;
            secondIndex--;
        }
    }

    /**
     * Tests to see that stacks are input properly if one stack
     * fills double stack while other is empty
     */
    @BeforeClass
    public static void doubleStackInputTest2() {
        DoubleStack doubleStack = (DoubleStack) getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        Stack Stack1 = new Stack(5);
        Stack Stack2 = new Stack(10);
        for (int i = 0; i < 10; i++) {
            Object element = i;
            try {
                Stack2.push(element);
            } catch (StackOverflowException e) {
                e.printStackTrace();
            }
        }
        assertEquals(Stack1.size(), 0);
        assertEquals(10, Stack2.size());
        assertEquals(Stack1.size() + Stack2.size(), doubleStack.getDoubleStack().length);
        try {
            doubleStack.insertStackValues(Stack1, Stack2);
        } catch (StacksTooBigException e) {
            e.printStackTrace();
        }
        int stackIndex = 9;
        for (int i = 0; i < 10; i++) {
            assertEquals(i, doubleStack.getDoubleStack()[stackIndex]);
            stackIndex--;
        }
    }

    /**
     * Tests to see that stacks are input properly and that
     * one stack can fill up any space not used by the other
     */
    @BeforeClass
    public static void doubleStackInputTest3() {
        DoubleStack doubleStack = (DoubleStack) getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        Stack Stack1 = new Stack(5);
        Stack Stack2 = new Stack(8);
        for (int i = 0; i < 8; i++) {
            Object element = i;
            try {
                if (i < 2) {
                    Stack1.push(element);
                }
                Stack2.push(element);
            } catch (StackOverflowException e) {
                e.printStackTrace();
            }
        }
        assertEquals(Stack1.size(), 2);
        assertEquals(8, Stack2.size());
        assertEquals(Stack1.size() + Stack2.size(), doubleStack.getDoubleStack().length);
        try {
            doubleStack.insertStackValues(Stack1, Stack2);
        } catch (StacksTooBigException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests to confirm that stacks input into double stack are unable
     * to be input when sum of stacks exceed size of double stack
     * and prints appropriate message
     */
    @BeforeClass
    public static void doubleStackInputExceptionTest() {
        DoubleStack doubleStack = (DoubleStack) getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        Stack Stack1 = new Stack(5);
        Stack Stack2 = new Stack(10);
        for (int i = 0; i < 5; i++) {
            Object element = 1;
            try {
                Stack1.push(element);
                Stack2.push(element);
            } catch (StackOverflowException e) {
                e.printStackTrace();
            }
        }
        assertEquals(Stack1.size(), 5);
        assertEquals(Stack1.size(), Stack2.size());
        assertEquals(Stack1.size() + Stack2.size(), doubleStack.getDoubleStack().length);
        try {
            doubleStack.insertStackValues(Stack1, Stack2);
        } catch (StacksTooBigException e) {
            e.printStackTrace();
        }
        Object element = 1;
        try {
            Stack2.push(element);
        } catch (StackOverflowException e) {
            e.printStackTrace();
        }
        try {
            doubleStack.insertStackValues(Stack1, Stack2);
        } catch (StacksTooBigException e) {
            System.out.println("Exception caught when stack size sum exceeds size of double stack");
        }
    }

    /**
     * Tests to see that exceptions are properly thrown
     * when attempt is made on double stack to get stacks
     * before they have been input
     */
    @Test
    public void doubleStackGetStacksExceptionTest() {
        doubleStack.getFirstStack();
        doubleStack.getSecondStack();
    }

    /**
     * Tests that the factory constructs a non-null double stack.
     */
    @Before
    public void factoryReturnsNonNullDoubleStackObject() {

        IDoubleStack doubleStack1 = getFactory().makeDoubleStack(DEFAULT_MAX_SIZE);
        assertTrue("Failure: IFactory.makeDoubleStack returns null, expected non-null IDoubleStack object", doubleStack1 != null);
    }

    /**
     * tests that stack indexes return null values after they're initialized
     */
    @Before
    public void whatIsEmptyValueInObjectArray() {
        assertTrue(Stack1.getStack()[0] == null);
        assertTrue(Stack1.getStack()[1] == null);
    }

    /**
     * Tests to see if my method to find the bottom index of the
     * stack in the array is returning the proper values
     */
    @Test
    public void findBottomTest() {
        Object element = 1;
        Object element2 = 4;
        try {
            assertEquals(Stack1.findBottom(), -1);
            Stack1.push(element);
            assertEquals(Stack1.findBottom(), 0);
            Stack1.push(element2);
            assertEquals(Stack1.findBottom(), 1);
        } catch (StackOverflowException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests to see if size() returns proper sizes
     */
    @Test
    public void sizeTest() {
        Object element = 1;
        Object element2 = 4;
        try {
            assertEquals(Stack1.size(), 0);
            Stack1.push(element);
            assertEquals(Stack1.size(), 1);
            Stack1.push(element2);
            assertEquals(Stack1.size(), 2);
        } catch (StackOverflowException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests to see if isEmpty() returns proper values
     */
    @Test
    public void isEmptyTest() {
        assertTrue(Stack1.isEmpty());
        Object element = 1;
        try {
            Stack1.push(element);
            assertFalse(Stack1.isEmpty());
        } catch (StackOverflowException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests to see if clear() properly clears array
     */
    @Test
    public void clearTest() {
        Object element = 1;
        try {
            Stack1.push(element);
            assertEquals(Stack1.size(), 1);
            assertEquals(Stack1.getStack()[0], element);
            Stack1.clear();
            assertTrue(Stack1.isEmpty());
            assertEquals(Stack1.size(), 0);
            assertEquals(Stack1.getStack()[0], null);
        } catch (StackOverflowException e) {
            e.printStackTrace();
        }
    }

    /**
     * tests to see if top() returns correct object
     */
    @Test
    public void stackTopTest() {
        Object element = 2;
        Object element2 = 4;
        try {
            Stack1.push(element);
            Stack1.push(element2);
            assertEquals(Stack1.top(), element2);
        } catch (StackEmptyException e) {
            e.printStackTrace();
        } catch (StackOverflowException e) {
            e.printStackTrace();
        }
    }

    /**
     * tests top exception
     */
    @Test
    public void stackTopExceptionTest() {
        try {
            Stack1.top();
        } catch (StackEmptyException e) {
            System.out.println("Exception caught when empty stack is topped");
        }
    }

    /**
     * tests to see if bottom index is being properly updated
     * using findBottom() and push() methods, then confirms that
     * elements are being pushed properly
     */
    @Test
    public void stackPushTests() {
        Object element = 1;
        Object element2 = 4;
        try {
            Stack1.push(element);
            assertEquals(Stack1.findBottom(), 0);
            assertEquals(true, Stack1.getStack()[0] == element);
            Stack1.push(element2);
            assertEquals(Stack1.findBottom(), 1);
            assertEquals(true, Stack1.getStack()[1] == element);
            assertEquals(true, Stack1.getStack()[0] == element2);
        } catch (StackOverflowException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests to see if push exception is called at proper time
     */
    @Test
    public void stackPushExceptionTest() {
        Object element = 1;
        try {
            for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
                Stack1.push(element);
            }
            assertEquals(DEFAULT_MAX_SIZE, Stack1.size());
            assertEquals(Stack1.findBottom(), DEFAULT_MAX_SIZE-1);
            Stack1.push(element);
        } catch (StackOverflowException e) {
            System.out.println("Exception caught when push is attempted on full stack");
        }

    }

    @Test
    public void stackPopTests() {
        Object element = 1;
        try {
            Stack1.push(element);
            assertEquals(Stack1.getStack()[0], element);
            assertEquals(element, Stack1.pop());
            assertEquals(Stack1.findBottom(), -1);
        } catch (StackOverflowException e) {
            e.printStackTrace();
        } catch (StackEmptyException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void stackPopExceptionTest() {
        try {
            Stack1.pop();
        } catch (StackEmptyException e) {
            System.out.println("Exception caught when empty stack is popped");
        }
    }
}
