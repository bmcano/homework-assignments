package Filters;

import Buffers.Buffer;

/**
 * @author Brandon Cano
 * <p>
 * Filter 4
 */
public class Translator implements Runnable {

    private final Buffer sharedInput;
    private final Buffer sharedOutput;

    public Translator(Buffer sharedInput, Buffer sharedOutput) {
        this.sharedInput = sharedInput;
        this.sharedOutput = sharedOutput;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String sequence = sharedInput.blockingGet();
                if (sequence.equals("TERMINATE")) break;

                char[] bases = sequence.toCharArray();
                StringBuilder output = new StringBuilder();

                for (int i = 0; i < bases.length; i += 3) {
                    if (bases.length - i >= 3) {
                        String subSequence = String.valueOf(bases[i]) + bases[i + 1] + bases[i + 2];
                        Codon codon = Codon.getCodon(subSequence);
                        output.append(codon.getOneLetterCode());
                    }
                }

                sharedOutput.blockingPut(output.toString());
            }
            sharedOutput.blockingPut("TERMINATE");
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
        System.out.println("--- Terminating Translator (4) ---");
    }
}
