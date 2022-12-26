import Buffers.BlockingBuffer;
import Buffers.Buffer;
import Filters.*;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadingDriver {
    public static void main(String[] args) {
        System.out.println("-- B15_MultithreadingDNA -- ");

        String dna;
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to enter your own DNA sequence?\n1. Yes\n2. No");
        if (input.next().equals("1")) {
            dna = input.next();
        } else {
            dna = "";
        }

        // create blocking buffers
        Buffer inputBuffer = new BlockingBuffer(1);
        Buffer buffer12 = new BlockingBuffer(1);
        Buffer buffer23 = new BlockingBuffer(2);
        Buffer buffer34 = new BlockingBuffer(3);
        Buffer buffer45 = new BlockingBuffer(1);
        Buffer outputBuffer = new BlockingBuffer(1);

        // create new thread pool
        ExecutorService executorService = Executors.newCachedThreadPool();

        // input filter - read data
        executorService.execute(new Input(inputBuffer, dna));

        // filter 1 - Gap Finder - 1 output
        executorService.execute(new GapFinder(inputBuffer, buffer12));

        // filter 2 - Reverse Complementor - 2 outputs
        executorService.execute(new ReverseComplementor(buffer12, buffer23));

        // filter 3 - Frame Builder - 3 outputs
        executorService.execute(new FrameBuilder(buffer23, buffer34));

        // filter 4 - Translator - 1 output
        executorService.execute(new Translator(buffer34, buffer45));

        // filter 5 - ORF Finder - 1 output
        executorService.execute(new ORFFinder(buffer45, outputBuffer));

        // output filter - display results
        executorService.execute(new Output(outputBuffer));

        executorService.shutdown();
    }
}
