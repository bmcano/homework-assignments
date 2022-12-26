import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Brandon Cano
 */
class EncodePOSTNETTest {

    @Test
    void zipTest() {
        EncodePOSTNET encode = new EncodePOSTNET();
        String expectedResult = "10101000101001010100100101010101";
        String actualResult = encode.encode("52242");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void zipPlusFourTest() {
        EncodePOSTNET encode = new EncodePOSTNET();
        String expectedResult = "1010100010100101010010010100011001010011001001010101";
        String actualResult = encode.encode("522421234");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void zipPlusFourPlusDeliveryTest() {
        EncodePOSTNET encode = new EncodePOSTNET();
        String expectedResult = "10001100101001100100101010011001000110010101001100000011010011";
        String actualResult = encode.encode("12345678901");
        assertEquals(expectedResult, actualResult);
    }
}
