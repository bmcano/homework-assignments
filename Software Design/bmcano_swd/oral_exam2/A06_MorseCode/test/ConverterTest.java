import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Brandon Cano
 */
class ConverterTest {

    @Test
    void toEnglishTestOne() {
        String expectedResult = Converter.toEnglish(".- / -... / -.-. / .---- ..--- ...--");
        String actualResult = "A B C 123";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void toEnglishTestTwo() {
        String expectedResult = Converter.toEnglish("- . ... - / -- . ... ... .- --. .");
        String actualResult = "TEST MESSAGE";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void toEnglishTestThree() {
        String expectedResult = Converter.toEnglish("-.-. --- -. ...- . .-. - / - --- / . -. --. .-.. .. ... ....");
        String actualResult = "CONVERT TO ENGLISH";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void toMorseCodeTestOne() {
        String expectedResult = Converter.toMorseCode("This is an English message");
        String actualResult = "- .... .. ... / .. ... / .- -. / . -. --. .-.. .. ... .... / -- . ... ... .- --. .";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void toMorseCodeTestTwo() {
        String expectedResult = Converter.toMorseCode("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        String actualResult = ".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --..";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void toMorseCodeTestThree() {
        String expectedResult = Converter.toMorseCode("Numbers 1234567890");
        String actualResult = "-. ..- -- -... . .-. ... / .---- ..--- ...-- ....- ..... -.... --... ---.. ----. -----";
        assertEquals(expectedResult, actualResult);
    }
}
