package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * This is a JUnit test suite for the ArrayDoubleStack and ArrayPriorityQueue classes.
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
   ArrayDoubleStackTests.class,
   ArrayPriorityQueueTests.class
})
public class Tests {

}
