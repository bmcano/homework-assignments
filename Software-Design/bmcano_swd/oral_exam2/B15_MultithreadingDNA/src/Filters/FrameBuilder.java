package Filters;

import Buffers.Buffer;

/**
 * @author Brandon Cano
 * <p>
 * Filter 3
 */
public class FrameBuilder implements Runnable {

    private final Buffer sharedInput;
    private final Buffer sharedOutput;

    public FrameBuilder(Buffer sharedInput, Buffer sharedOutput) {
        this.sharedInput = sharedInput;
        this.sharedOutput = sharedOutput;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String sequence = sharedInput.blockingGet();
                if (sequence.equals("TERMINATE")) break;

                sharedOutput.blockingPut(sequence);
                if (sequence.length() > 2) {
                    sharedOutput.blockingPut(sequence.substring(1));
                    sharedOutput.blockingPut(sequence.substring(2));
                }
            }

            sharedOutput.blockingPut("TERMINATE");
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
        System.out.println("--- Terminating Frame Builder (3) ---");
    }
}
