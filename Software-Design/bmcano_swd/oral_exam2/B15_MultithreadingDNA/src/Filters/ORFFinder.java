package Filters;

import Buffers.Buffer;

/**
 * @author Brandon Cano
 * <p>
 * Filter 5
 */
public class ORFFinder implements Runnable {

    private final Buffer sharedInput;
    private final Buffer sharedOutput;

    public ORFFinder(Buffer sharedInput, Buffer sharedOutput) {
        this.sharedInput = sharedInput;
        this.sharedOutput = sharedOutput;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String sequence = sharedInput.blockingGet();
                if (sequence.equals("TERMINATE")) break;

                int index = 0;
                StringBuilder segment = new StringBuilder();

                while (index < sequence.length()) {
                    if (sequence.charAt(index) == '*') {
                        if (segment.length() > 21) sharedOutput.blockingPut(segment.toString());
                        segment = new StringBuilder();
                    } else {
                        segment.append(sequence.charAt(index));
                    }
                    index++;
                }

                if (segment.length() > 21) {
                    sharedOutput.blockingPut(segment.toString());
                }
            }

            sharedOutput.blockingPut("TERMINATE");
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
        System.out.println("--- Terminating ORF Finder (5) ---");
    }
}
