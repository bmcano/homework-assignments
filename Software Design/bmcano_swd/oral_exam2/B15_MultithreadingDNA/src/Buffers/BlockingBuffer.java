package Buffers;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Brandon Cano
 */
public class BlockingBuffer implements Buffer {

    private final ArrayBlockingQueue<String> buffer;

    public BlockingBuffer(int capacity) {
        this.buffer = new ArrayBlockingQueue<>(capacity);
    }

    @Override
    public void blockingPut(String value) throws InterruptedException {
        // add item to the queue
        buffer.put(value);
    }

    @Override
    public String blockingGet() throws InterruptedException {
        // take the item out of the queue
        return buffer.take();
    }
}
