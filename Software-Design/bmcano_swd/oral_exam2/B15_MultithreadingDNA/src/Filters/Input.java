package Filters;

import Buffers.Buffer;

/**
 * @author Brandon Cano
 * <p>
 * Input Filter
 */
public class Input implements Runnable {

    private final String sequence;
    private final Buffer sharedOutput;

    public Input(Buffer sharedOutput, String dna) {
        if (dna.equals("")) {
            this.sequence = "tgaaacggtcttgacgtagctttgggggctcggagacgattattcgaggacccgcatccg " +
                    "ccgtggtcgcgataggctgctacctattcgttggtaacttacccgcaccgggaccaccggn " +
                    "cgttccagcccaaagtcccgtcgaaggg tctggtgtctaatggtgaatgtggctactgta " +
                    "cacgcaaggcggctattaccgtgg aagtcacttggtttcgggcattgcgctccccgcgaan" +
                    " cacggctcccctgattggagccgcgacagatacagtactcgtccgacgacgtcgacgagg " +
                    "ggccgagaaaagaggacatgtgtaggctcaacacactagagtagcgagtt cagctcgagan" +
                    " ctgtggaaagggacagacttgacacagatatacatcgtgtgctcctatgcccgttccatg " +
                    "tgttagcagcactacacacgagcgaacgtccagtacgagagctaaccgttcaggtagacgn " +
                    "ggtcaaaagcctcatggcttaatgattacgggcagcattgaagtt tggtaatgtgtaacg " +
                    "cggttcgtcagtcaatcccatcaccacgaagcgtctagcatgcccgaaggagaaaggtaan " +
                    "gctgacgcgcggtaca tgcatggcacccagttgttaacgcctcactcgaagcaagcggca " +
                    "ggctcccaagcgtccgcctcaagagatagtaccagacaacctaaggtccgatattaagccn " +
                    "gagctccctgggtctctaccccctatacatggattaaccgccatgatgaactc tatagcc " +
                    "cgtgattggttcacttgattgtattcccgcaactagaactttcagctggtatcg cttcgtn" +
                    " tcgggagaggtagccggaatagctttaggcactgaacactagtcggcgtgctagaccccg " +
                    "acggggtatccaacttctcctgtgttggtgtccgaggacgcatcttctggatcatcggtgn " +
                    "ccaggagggtcgggtgcgatgagacacgtgaactcggtttagctagagctttcagaggta  ";
        } else {
            this.sequence = dna;
        }

        this.sharedOutput = sharedOutput;
    }

    @Override
    public void run() {
        try {
            sharedOutput.blockingPut(sequence.toUpperCase());
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
        System.out.println("--- Terminating Input Filter ---");
    }

    /* output from current input
    PVVPVRVSYQRIGSSLSRPRRMRVLE
    VAAYRDHGGCGSSNNRLRAPKATSRPF
    QPIATTADAGPRIIVSEPPKLRQDRF
    SRGAQCPKPSDFHGNSRLACTVATFTIRHQTLRRDFGLE
    AYTCPLFSAPRRRRRTSTVSVAAPIRGAV
    CVEPTHVLFSRPLVDVVGRVLYLSRLQSGEP
    TRYSSVLSLHMSSFLGPSSTSSDEYCICRGSNQGSR
    RLPERLALVLDVRSCVVLLTHGTGIGAHDVYLCQVCPFPQ
    TVSSRTGRSLVCSAANTWNGHRSTRCISVSSLSLST
    LPFSFGHARRFVVMGLTDEPRYTLPNFNAARNH
    YRTLGCLVLSLEADAWEPAACFE
    VVWYYLLRRTLGSLPLASSEALTTGCHACTARQ
    TKRYQLKVLVAGIQSSEPITGYRVHHGG
    LREYNQVNQSRAIEFIMAVNPCIGGRDPGS
    SRRCVLGHQHRRSWIPRRGLARRLVFSA
    TDDPEDASSDTNTGEVGYPVGV
    PMIQKMRPRTPTQEKLDTPSGSSTPTSVQCLKLFRLPLP
     */
}
