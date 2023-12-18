package Buffers;

/**
 * @author Brandon Cano
 */
public interface Buffer {

    /**
     * This will take a value and add it to the queue and will wait if full.
     *
     * @param value to be added to the ArrayBlockingQueue
     * @throws InterruptedException if something is interrupted
     */
    void blockingPut(String value) throws InterruptedException;

    /**
     * This will take the value out of the front of the queue, or wait till an item is present.
     *
     * @return the value being taken out of the queue
     * @throws InterruptedException if something is interrupted
     */
    String blockingGet() throws InterruptedException;
}
