package Filters;

import Buffers.Buffer;

/**
 * @author Brandon Cano
 * <p>
 * Filter 1
 */
public class GapFinder implements Runnable {

    private final Buffer sharedInput;
    private final Buffer sharedOutput;

    public GapFinder(Buffer sharedInput, Buffer sharedOutput) {
        this.sharedInput = sharedInput;
        this.sharedOutput = sharedOutput;
    }

    @Override
    public void run() {
        try {
            String sequence = sharedInput.blockingGet();

            // in case input has newlines
            sequence = sequence.replace("\n", "");
            StringBuilder dnaSegment = new StringBuilder();

            for (int i = 0; i < sequence.length(); i++) {
                // send next 'gap' once an 'N' is found
                if (sequence.charAt(i) == 'N' && dnaSegment.length() > 0) {
                    sharedOutput.blockingPut(dnaSegment.toString());
                    dnaSegment = new StringBuilder();
                }

                if (isBase(sequence.charAt(i))) {
                    dnaSegment.append(sequence.charAt(i));
                }
            }

            sharedOutput.blockingPut(dnaSegment.toString());
            sharedOutput.blockingPut("TERMINATE");
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
        System.out.println("--- Terminating Gap Filter (1) ---");
    }

    /**
     * This will return true if the character is any one of the four bases; A, T, G, or C.
     *
     * @param character is a char that represents a potential base
     * @return boolean value determining if the char is a base
     */
    private boolean isBase(char character) {
        return character == 'A' || character == 'T' || character == 'G' || character == 'C';
    }
}
