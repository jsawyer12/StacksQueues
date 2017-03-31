package impl;

import interfaces.IDoubleStack;
import interfaces.IFactory;
import interfaces.IPriorityQueue;

/**
 * This class implements a singleton factory.
 *
 */
public final class Factory implements IFactory {

    private static IFactory factoryInstance = null;

    private Factory() {

    }

    /**
     * Method which returns an instance of the singleton Factory class.
     * @return the instance of the Factory
     */
    public static IFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new Factory();
        }
        return factoryInstance;
    }

    @Override
    public IDoubleStack makeDoubleStack(int maxSize) {
        IDoubleStack doubleStack = new DoubleStack(maxSize);
        return doubleStack;
    }

    @Override
    public IPriorityQueue makePriorityQueue(int maxSize) {
        IPriorityQueue priorityQueue = new PriorityQueue(maxSize);
        return priorityQueue;
    }

}
