package Filters;

import Buffers.Buffer;

/**
 * @author Brandon Cano
 * <p>
 * Filter 2
 */
public class ReverseComplementor implements Runnable {

    private final Buffer sharedInput;
    private final Buffer sharedOutput;

    public ReverseComplementor(Buffer sharedInput, Buffer sharedOutput) {
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
                String reversedString = reverseString(sequence);
                String reversedComplement = toComplement(reversedString);
                sharedOutput.blockingPut(reversedComplement);
            }

            sharedOutput.blockingPut("TERMINATE");
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
        System.out.println("--- Terminating Reverse Complementor (2) ---");
    }

    /**
     * Reverses the input string.
     *
     * @param string a sequence of DNA bases, A, G, T, or C
     * @return the reversed string
     */
    private String reverseString(String string) {
        return new StringBuilder(string).reverse().toString();
    }

    /**
     * This will find the complements of each individual base.
     *
     * @param string a sequence of DNA bases, A, G, T, or C
     * @return the compliment of the sequence: A->T, T->A, G->C, C->G
     */
    private String toComplement(String string) {
        StringBuilder complement = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == 'A') complement.append("T");
            if (string.charAt(i) == 'T') complement.append("A");
            if (string.charAt(i) == 'G') complement.append("C");
            if (string.charAt(i) == 'C') complement.append("G");
        }
        return complement.toString();
    }
}
