package Filters;

import Buffers.Buffer;

public class Output implements Runnable {

    private final Buffer sharedInput;

    public Output(Buffer sharedInput) {
        this.sharedInput = sharedInput;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String sequence = sharedInput.blockingGet();
                if (sequence.equals("TERMINATE")) break;

                System.out.println(sequence);
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
        System.out.println("--- Terminating Output Filter ---");
    }
}
