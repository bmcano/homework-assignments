import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Brandon Cano
 */
class EncodeUPCATest {

    @Test
    void encodeTest1() {
        EncodeUPCA encode = new EncodeUPCA();
        String expectedResult = "10100011010011001001001101100010100011010111101010101000010001001000010100010010011101011100101";
        String actualResult = encode.encode("01254667375");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void encodeTest2() {
        EncodeUPCA encode = new EncodeUPCA();
        String expectedResult = "10100011010011001010111101100010110111010001101010110011010000101000010101110010011101011100101";
        String actualResult = encode.encode("01658413345");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void encodeTest3() {
        EncodeUPCA encode = new EncodeUPCA();
        String expectedResult = "10100011010011001010001101111010110111001100101010101000011101001000100101110010011101101100101";
        String actualResult = encode.encode("01438169745");
        assertEquals(expectedResult, actualResult);
    }
}
