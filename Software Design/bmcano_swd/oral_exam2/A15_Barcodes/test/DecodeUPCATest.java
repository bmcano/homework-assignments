import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Brandon Cano
 */
class DecodeUPCATest {

    @Test
    void decodeTest1() {
        DecodeUPCA decode = new DecodeUPCA();
        String expectedResult = "01254667375";
        String actualResult = decode.decode("10100011010011001001001101100010100011010111101010101000010001001000010100010010011101011100101");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void decodeTest2() {
        DecodeUPCA decode = new DecodeUPCA();
        String expectedResult = "01658413345";
        String actualResult = decode.decode("10100011010011001010111101100010110111010001101010110011010000101000010101110010011101011100101");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void decodeTest3() {
        DecodeUPCA decode = new DecodeUPCA();
        String expectedResult = "01438169745";
        String actualResult = decode.decode("10100011010011001010001101111010110111001100101010101000011101001000100101110010011101101100101");
        assertEquals(expectedResult, actualResult);
    }
}
