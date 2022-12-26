import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Brandon Cano
 * A14_FourSquare
 */
class CipherTest {

    // Encryption Tests
    @Test
    void encryptionTestOne() {
        String keywordOne = "EXAMPLE";
        String keywordTwo = "KEYWORD";

        Cipher cipher = new Cipher(keywordOne, keywordTwo);

        String expectedResult = "FYGMKYHOBXMFKKKIMD";
        String actualResult = cipher.encrypt("HELP ME OBIWAN KENOBI");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void encryptionTestTwo() {
        String keywordOne = "LOREM";
        String keywordTwo = "IPSYUM";

        Cipher cipher = new Cipher(keywordOne, keywordTwo);

        String expectedResult = "MHKFSLDOREERMGIORUUYUONPECTMSSDHFPJASOEUMHEDSRKGEORJUETAIYDYDYTJUOPHOIHRMUTYHJHRRJOMGYOEFO";
        String actualResult = cipher.encrypt("dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void encryptionTestThree() {
        String keywordOne = "STAR";
        String keywordTwo = "TREK";

        Cipher cipher = new Cipher(keywordOne, keywordTwo);

        String expectedResult = "ULBIKRKWGIXCTURMLMLKCENDKMTASGIU";
        String actualResult = cipher.encrypt("TO BOLDLY GO WHERE NO ONE HAS GONE BEFORE");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void encryptionTestFour() {
        String keywordOne = "LORD";
        String keywordTwo = "RINGS";

        Cipher cipher = new Cipher(keywordOne, keywordTwo);

        String expectedResult = "KLOUFKFOIUPLDUGNHNIHKLOUFKFOHEFKDTGNMJMGTBIDUKOOFKFOGNHNIHDFDDKTGNLGNHMGSPDBKGSDRL";
        String actualResult = cipher.encrypt("ONE RING TO RULE THEM ALL ONE RING TO FIND THEM ONE RING TO BRING THEM ALL AND IN THE DARKNESS BIND THEM");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void encryptDecryptTestFive() {
        String keywordOne = "HARRY";
        String keywordTwo = "POTTER";

        Cipher cipher = new Cipher(keywordOne, keywordTwo);

        String expectedResult = "ZEOMAVGYALRDALSW";
        String actualResult = cipher.encrypt("Yerr a wizard Harry");
        assertEquals(expectedResult, actualResult);
    }

    // Decryption Tests
    @Test
    void decryptionTestOne() {
        String keywordOne = "EXAMPLE";
        String keywordTwo = "KEYWORD";

        Cipher cipher = new Cipher(keywordOne, keywordTwo);

        String expectedResult = "HELPMEOBIWANKENOBI";
        String actualResult = cipher.decrypt("FYGMKYHOBXMFKKKIMD");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void decryptionTestTwo() {
        String keywordOne = "LOREM";
        String keywordTwo = "IPSYUM";

        Cipher cipher = new Cipher(keywordOne, keywordTwo);

        String expectedResult = "DOLORSITAMETCONSECTETURADIPISCINGELITSEDDOEIUSMODTEMPORINCIDIDUNTUTLABOREETDOLOREMAGNAALIU";
        String actualResult = cipher.decrypt("MHKFSLDOREERMGIORUUYUONPECTMSSDHFPJASOEUMHEDSRKGEORJUETAIYDYDYTJUOPHOIHRMUTYHJHRRJOMGYOEFO");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void decryptionTestThree() {
        String keywordOne = "STAR";
        String keywordTwo = "TREK";

        Cipher cipher = new Cipher(keywordOne, keywordTwo);

        String expectedResult = "TOBOLDLYGOWHERENOONEHASGONEBEFOR";
        String actualResult = cipher.decrypt("ULBIKRKWGIXCTURMLMLKCENDKMTASGIU");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void decryptionTestFour() {
        String keywordOne = "LORD";
        String keywordTwo = "RINGS";

        Cipher cipher = new Cipher(keywordOne, keywordTwo);

        String expectedResult = "ONERINGTORULETHEMALLONERINGTOFINDTHEMONERINGTOBRINGTHEMALLANDINTHEDARKNESSBINDTHEM";
        String actualResult = cipher.decrypt("KLOUFKFOIUPLDUGNHNIHKLOUFKFOHEFKDTGNMJMGTBIDUKOOFKFOGNHNIHDFDDKTGNLGNHMGSPDBKGSDRL");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void decryptionTestFive() {
        String keywordOne = "HARRY";
        String keywordTwo = "POTTER";

        Cipher cipher = new Cipher(keywordOne, keywordTwo);

        String expectedResult = "YERRAWIZARDHARRY";
        String actualResult = cipher.decrypt("ZEOMAVGYALRDALSW");
        assertEquals(expectedResult, actualResult);
    }
}