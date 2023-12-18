import Filters.Codon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Brandon Cano
 */
class CodonTest {

    @Test
    void uxxThreeLetterCodeTest() {
        assertEquals("Phe", Codon.UUU.getThreeLetterCode());
        assertEquals("Phe", Codon.UUC.getThreeLetterCode());
        assertEquals("Leu", Codon.UUA.getThreeLetterCode());
        assertEquals("Leu", Codon.UUG.getThreeLetterCode());
        assertEquals("Ser", Codon.UCU.getThreeLetterCode());
        assertEquals("Ser", Codon.UCC.getThreeLetterCode());
        assertEquals("Ser", Codon.UCA.getThreeLetterCode());
        assertEquals("Ser", Codon.UCG.getThreeLetterCode());
        assertEquals("Tyr", Codon.UAU.getThreeLetterCode());
        assertEquals("Tyr", Codon.UAC.getThreeLetterCode());
        assertEquals("Stop", Codon.UAA.getThreeLetterCode());
        assertEquals("Stop", Codon.UAG.getThreeLetterCode());
        assertEquals("Cys", Codon.UGU.getThreeLetterCode());
        assertEquals("Cys", Codon.UGC.getThreeLetterCode());
        assertEquals("Stop", Codon.UGA.getThreeLetterCode());
        assertEquals("Trp", Codon.UGG.getThreeLetterCode());
    }

    @Test
    void uxxOneLetterCodeTest() {
        assertEquals('F', Codon.UUU.getOneLetterCode());
        assertEquals('F', Codon.UUC.getOneLetterCode());
        assertEquals('L', Codon.UUA.getOneLetterCode());
        assertEquals('L', Codon.UUG.getOneLetterCode());
        assertEquals('S', Codon.UCU.getOneLetterCode());
        assertEquals('S', Codon.UCC.getOneLetterCode());
        assertEquals('S', Codon.UCA.getOneLetterCode());
        assertEquals('S', Codon.UCG.getOneLetterCode());
        assertEquals('Y', Codon.UAU.getOneLetterCode());
        assertEquals('Y', Codon.UAC.getOneLetterCode());
        assertEquals('*', Codon.UAA.getOneLetterCode());
        assertEquals('*', Codon.UAG.getOneLetterCode());
        assertEquals('C', Codon.UGU.getOneLetterCode());
        assertEquals('C', Codon.UGC.getOneLetterCode());
        assertEquals('*', Codon.UGA.getOneLetterCode());
        assertEquals('W', Codon.UGG.getOneLetterCode());
    }

    @Test
    void cxxThreeLetterCodeTest() {
        assertEquals("Leu", Codon.CUU.getThreeLetterCode());
        assertEquals("Leu", Codon.CUC.getThreeLetterCode());
        assertEquals("Leu", Codon.CUA.getThreeLetterCode());
        assertEquals("Leu", Codon.CUG.getThreeLetterCode());
        assertEquals("Pro", Codon.CCU.getThreeLetterCode());
        assertEquals("Pro", Codon.CCC.getThreeLetterCode());
        assertEquals("Pro", Codon.CCA.getThreeLetterCode());
        assertEquals("Pro", Codon.CCG.getThreeLetterCode());
        assertEquals("His", Codon.CAU.getThreeLetterCode());
        assertEquals("His", Codon.CAC.getThreeLetterCode());
        assertEquals("Gln", Codon.CAA.getThreeLetterCode());
        assertEquals("Gln", Codon.CAG.getThreeLetterCode());
        assertEquals("Arg", Codon.CGU.getThreeLetterCode());
        assertEquals("Arg", Codon.CGC.getThreeLetterCode());
        assertEquals("Arg", Codon.CGA.getThreeLetterCode());
        assertEquals("Arg", Codon.CGG.getThreeLetterCode());
    }

    @Test
    void cxxOneLetterCodeTest() {
        assertEquals('L', Codon.CUU.getOneLetterCode());
        assertEquals('L', Codon.CUC.getOneLetterCode());
        assertEquals('L', Codon.CUA.getOneLetterCode());
        assertEquals('L', Codon.CUG.getOneLetterCode());
        assertEquals('P', Codon.CCU.getOneLetterCode());
        assertEquals('P', Codon.CCC.getOneLetterCode());
        assertEquals('P', Codon.CCA.getOneLetterCode());
        assertEquals('P', Codon.CCG.getOneLetterCode());
        assertEquals('H', Codon.CAU.getOneLetterCode());
        assertEquals('H', Codon.CAC.getOneLetterCode());
        assertEquals('Q', Codon.CAA.getOneLetterCode());
        assertEquals('Q', Codon.CAG.getOneLetterCode());
        assertEquals('R', Codon.CGU.getOneLetterCode());
        assertEquals('R', Codon.CGC.getOneLetterCode());
        assertEquals('R', Codon.CGA.getOneLetterCode());
        assertEquals('R', Codon.CGG.getOneLetterCode());
    }

    @Test
    void axxThreeLetterCodeTest() {
        assertEquals("Ile", Codon.AUU.getThreeLetterCode());
        assertEquals("Ile", Codon.AUC.getThreeLetterCode());
        assertEquals("Ile", Codon.AUA.getThreeLetterCode());
        assertEquals("Met", Codon.AUG.getThreeLetterCode());
        assertEquals("Thr", Codon.ACU.getThreeLetterCode());
        assertEquals("Thr", Codon.ACC.getThreeLetterCode());
        assertEquals("Thr", Codon.ACA.getThreeLetterCode());
        assertEquals("Thr", Codon.ACG.getThreeLetterCode());
        assertEquals("Asn", Codon.AAU.getThreeLetterCode());
        assertEquals("Asn", Codon.AAC.getThreeLetterCode());
        assertEquals("Lys", Codon.AAA.getThreeLetterCode());
        assertEquals("Lys", Codon.AAG.getThreeLetterCode());
        assertEquals("Ser", Codon.AGU.getThreeLetterCode());
        assertEquals("Ser", Codon.AGC.getThreeLetterCode());
        assertEquals("Arg", Codon.AGA.getThreeLetterCode());
        assertEquals("Arg", Codon.AGG.getThreeLetterCode());
    }

    @Test
    void axxOneLetterCodeTest() {
        assertEquals('I', Codon.AUU.getOneLetterCode());
        assertEquals('I', Codon.AUC.getOneLetterCode());
        assertEquals('I', Codon.AUA.getOneLetterCode());
        assertEquals('M', Codon.AUG.getOneLetterCode());
        assertEquals('T', Codon.ACU.getOneLetterCode());
        assertEquals('T', Codon.ACC.getOneLetterCode());
        assertEquals('T', Codon.ACA.getOneLetterCode());
        assertEquals('T', Codon.ACG.getOneLetterCode());
        assertEquals('N', Codon.AAU.getOneLetterCode());
        assertEquals('N', Codon.AAC.getOneLetterCode());
        assertEquals('K', Codon.AAA.getOneLetterCode());
        assertEquals('K', Codon.AAG.getOneLetterCode());
        assertEquals('S', Codon.AGU.getOneLetterCode());
        assertEquals('S', Codon.AGC.getOneLetterCode());
        assertEquals('R', Codon.AGA.getOneLetterCode());
        assertEquals('R', Codon.AGG.getOneLetterCode());
    }

    @Test
    void gxxThreeLetterCodeTest() {
        assertEquals("Val", Codon.GUU.getThreeLetterCode());
        assertEquals("Val", Codon.GUC.getThreeLetterCode());
        assertEquals("Val", Codon.GUA.getThreeLetterCode());
        assertEquals("Val", Codon.GUG.getThreeLetterCode());
        assertEquals("Ala", Codon.GCU.getThreeLetterCode());
        assertEquals("Ala", Codon.GCC.getThreeLetterCode());
        assertEquals("Ala", Codon.GCA.getThreeLetterCode());
        assertEquals("Ala", Codon.GCG.getThreeLetterCode());
        assertEquals("Asp", Codon.GAU.getThreeLetterCode());
        assertEquals("Asp", Codon.GAC.getThreeLetterCode());
        assertEquals("Glu", Codon.GAA.getThreeLetterCode());
        assertEquals("Glu", Codon.GAG.getThreeLetterCode());
        assertEquals("Gly", Codon.GGU.getThreeLetterCode());
        assertEquals("Gly", Codon.GGC.getThreeLetterCode());
        assertEquals("Gly", Codon.GGA.getThreeLetterCode());
        assertEquals("Gly", Codon.GGG.getThreeLetterCode());
    }

    @Test
    void gxxOneLetterCodeTest() {
        assertEquals('V', Codon.GUU.getOneLetterCode());
        assertEquals('V', Codon.GUC.getOneLetterCode());
        assertEquals('V', Codon.GUA.getOneLetterCode());
        assertEquals('V', Codon.GUG.getOneLetterCode());
        assertEquals('A', Codon.GCU.getOneLetterCode());
        assertEquals('A', Codon.GCC.getOneLetterCode());
        assertEquals('A', Codon.GCA.getOneLetterCode());
        assertEquals('A', Codon.GCG.getOneLetterCode());
        assertEquals('D', Codon.GAU.getOneLetterCode());
        assertEquals('D', Codon.GAC.getOneLetterCode());
        assertEquals('E', Codon.GAA.getOneLetterCode());
        assertEquals('E', Codon.GAG.getOneLetterCode());
        assertEquals('G', Codon.GGU.getOneLetterCode());
        assertEquals('G', Codon.GGC.getOneLetterCode());
        assertEquals('G', Codon.GGA.getOneLetterCode());
        assertEquals('G', Codon.GGG.getOneLetterCode());
    }

    @Test
    void getCodonTest() {
        assertEquals(Codon.UUU, Codon.getCodon("UUU"));
        assertEquals(Codon.CCC, Codon.getCodon("CCC"));
        assertEquals(Codon.AAA, Codon.getCodon("AAA"));
        assertEquals(Codon.GGG, Codon.getCodon("GGG"));

        assertEquals(Codon.UGU, Codon.getCodon("UGU"));
        assertEquals(Codon.CUC, Codon.getCodon("CUC"));
        assertEquals(Codon.ACA, Codon.getCodon("ACA"));
        assertEquals(Codon.GAG, Codon.getCodon("GAG"));

        // illegal arguments
        assertThrows(IllegalArgumentException.class, () -> Codon.getCodon("UCAG"));
        assertThrows(IllegalArgumentException.class, () -> Codon.getCodon("XXX"));
        assertThrows(IllegalArgumentException.class, () -> Codon.getCodon(" "));
        assertThrows(IllegalArgumentException.class, () -> Codon.getCodon(""));
    }
}