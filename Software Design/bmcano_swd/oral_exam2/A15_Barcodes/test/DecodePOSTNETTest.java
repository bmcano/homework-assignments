import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Brandon Cano
 */
class DecodePOSTNETTest {

    @Test
    void zipTest() {
        DecodePOSTNET decode = new DecodePOSTNET();
        String expectedResult = "52242";
        String actualResult = decode.decode("10101000101001010100100101010101");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void zipPlusFourTest() {
        DecodePOSTNET decode = new DecodePOSTNET();
        String expectedResult = "522421234";
        String actualResult = decode.decode("1010100010100101010010010100011001010011001001010101");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void zipPlusFourPlusDeliveryTest() {
        DecodePOSTNET decode = new DecodePOSTNET();
        String expectedResult = "12345678901";
        String actualResult = decode.decode("10001100101001100100101010011001000110010101001100000011010011");
        assertEquals(expectedResult, actualResult);
    }
}
