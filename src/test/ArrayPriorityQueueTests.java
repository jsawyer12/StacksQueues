package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import common.QueueEmptyException;
import common.QueueFullException;
import impl.Factory;
import impl.PriorityQueue;
import interfaces.IFactory;
import org.junit.Test;

import common.AbstractFactoryClient;
import interfaces.IPriorityQueue;

/**
 * Tests priority queue implementation.
 */
public class ArrayPriorityQueueTests extends AbstractFactoryClient {

    private static final int DEFAULT_MAX_SIZE = 10;

    PriorityQueue queue = (PriorityQueue) getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);

    /**
     * Tests that the factory constructs a non-null priority queue.
     */
    @Test
    public void factoryReturnsNonNullDoubleStackObject() {

        IPriorityQueue queue = getFactory().makePriorityQueue(DEFAULT_MAX_SIZE);
        assertTrue("Failure: IFactory.makePriorityQueue returns null, expected non-null IPriorityQueue object", queue != null);
    }

    @Test
    public void clearTest() {
        Comparable element1 = 2;
        Comparable element2 = 5;
        Comparable element3 = 3;
        Comparable element4 = 8;
        try {
            queue.enqueue(element1);
            queue.enqueue(element2);
            queue.enqueue(element3);
            queue.enqueue(element4);
        } catch (QueueFullException e) {
            e.printStackTrace();
        }
        assertFalse(queue.isEmpty());
        try {
            assertEquals(element4, queue.dequeue());
        } catch (QueueEmptyException e) {
            e.printStackTrace();
        }
        queue.clear();
        assertTrue(queue.isEmpty());
    }

    /**
     * Tests to see if dequeue functions for one input
     */
    @Test
    public void dequeueTest() {
        Comparable element = 1;
        try {
            queue.enqueue(element);
        } catch (QueueFullException e) {
            e.printStackTrace();
        }
        assertFalse(queue.isEmpty());
        try {
            assertEquals(element, queue.dequeue());
        } catch (QueueEmptyException e) {
            e.printStackTrace();
        }
        assertTrue(queue.isEmpty());
    }


    /**
     * Tests to see if element with highest value is returned from dequeue
     * and is deleted from the array
     */
    @Test
    public void dequeueTest2() {
        Comparable element = 1;
        Comparable element2 = 5;
        Comparable element3 = 4;
        Comparable element4 = 8;
        try {
            queue.enqueue(element);
            queue.enqueue(element2);
            queue.enqueue(element3);
            queue.enqueue(element4);
        } catch (QueueFullException e) {
            e.printStackTrace();
        }
        assertFalse(queue.isEmpty());
        try {
            assertEquals(element4, queue.dequeue());
        } catch (QueueEmptyException e) {
            e.printStackTrace();
        }
        assertEquals(queue.size(), 3);
    }

    /**
     * Extension: orders elements that are characters with the latest letters
     * in the alphabet as the highest value
     */
    @Test
    public void dequeueTestExtension() {
        Comparable element = 'a';
        Comparable element2 = 'c';
        Comparable element3 = 'd';
        Comparable element4 = 'b';
        try {
            queue.enqueue(element);
            queue.enqueue(element2);
            queue.enqueue(element3);
            queue.enqueue(element4);
        } catch (QueueFullException e) {
            e.printStackTrace();
        }
        assertFalse(queue.isEmpty());
        try {
            assertEquals(element3, queue.dequeue());
        } catch (QueueEmptyException e) {
            e.printStackTrace();
        }
    }

    /**
     * Extension: orders elements that are Strings, with the latest letters
     * in the alphabet as the highest value
     */
    @Test
    public void dequeueTestExtension2() {
        Comparable element = "balkasdf";
        Comparable element2 = "ajfgsda";
        Comparable element3 = "jkhhsdfg";
        Comparable element4 = "asjgsdrg";
        try {
            queue.enqueue(element);
            queue.enqueue(element2);
            queue.enqueue(element3);
            queue.enqueue(element4);
        } catch (QueueFullException e) {
            e.printStackTrace();
        }
        assertFalse(queue.isEmpty());
        try {
            assertEquals(element3, queue.dequeue());
        } catch (QueueEmptyException e) {
            e.printStackTrace();
        }
    }


    /**
     * tests that isEmpty returns proper values
     */
    @Test
    public void isEmptyEnqueueTest() {
        assertTrue(queue.isEmpty());
        Comparable element = 1;
        try {
            queue.enqueue(element);
            assertFalse(queue.isEmpty());
        } catch (QueueFullException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests to see if enqueue properly stores comparable elements in queue
     */
    @Test
    public void enqueueTest() {
        assertTrue(queue.isEmpty());
        Comparable element = 1;
        Comparable element2 = 4;
        try {
            queue.enqueue(element);
            queue.enqueue(element2);
        } catch (QueueFullException e) {
            e.printStackTrace();
        }
        assertEquals(element, queue.getPriorityQueue()[0]);
        assertEquals(element2, queue.getPriorityQueue()[1]);
    }

    /**
     * Tests to see if QueueFullException is thrown on enqueue attempt
     * when queue is already full
     */
    @Test
    public void enqueueExceptionTest() {
        try {
            for (int i = 0; i < DEFAULT_MAX_SIZE; i++) {
                Comparable element = i;
                queue.enqueue(element);
            }
            assertEquals(queue.size(), 10);
        } catch (QueueFullException e) {
            e.printStackTrace();
        }
        Comparable element = "a";
        try {
            queue.enqueue(element);
        } catch (QueueFullException e) {
            System.out.println("Exception thrown when enqueue is attempted on full queue");
        }
    }
}
