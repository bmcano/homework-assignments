import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * @author Brandon Cano
 */
public class EncodeUPCA {

    private final Map<String, String> toBinaryLeft = new HashMap<>();
    private final Map<String, String> toBinaryRight = new HashMap<>();

    public EncodeUPCA() {
        String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] leftCodes = {"0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011"};
        String[] rightCodes = {"1110010", "1100110", "1101100", "1000010", "1011100", "1001110", "1010000", "1000100", "1001000", "1110100"};

        for (int i = 0; i < 10; i++) {
            toBinaryLeft.put(digits[i], leftCodes[i]);
            toBinaryRight.put(digits[i], rightCodes[i]);
        }
    }

    /**
     * This will take a product code and properly encode it for UPCA barcodes.
     *
     * @param productCode code as a String to be encoded
     * @return the encoded product code
     */
    public String encode(String productCode) {
        StringBuilder codeLeft = new StringBuilder();
        StringBuilder codeRight = new StringBuilder();
        String[] digits = productCode.split("");
        for (int i = 0; i < digits.length; i++) {
            if (i < 6) {
                codeLeft.append(toBinaryLeft.get(digits[i]));
            } else {
                codeRight.append(toBinaryRight.get(digits[i]));
            }
        }
        String checkSum = toBinaryRight.get(checkSum(productCode));
        return "101" + codeLeft + "01010" + codeRight + checkSum + "101";
    }

    /**
     * This is a private helper method so that the encoding method will be able to find the last digit of the barcode.
     *
     * @param productCode a string that has the zipCode
     * @return the checkSum operation to add the final number to the barcode
     */
    private String checkSum(String productCode) {
        int total = digitTotal(productCode);
        if (total % 10 != 0) return String.valueOf(10 - (total % 10));
        return "0";
    }

    /**
     * This is a private helper method which will sum the digits in a zip code so that checkSum can be called.
     *
     * @param productCode a String of the decimal zip code
     * @return the sum of the digits in the zip code
     */
    private int digitTotal(String productCode) {
        String[] digits = productCode.split("");
        int odds = 0;
        int evens = 0;

        for (int i = 0; i < digits.length; i++) {
            if ((i + 1) % 2 == 0) { // evens
                evens += parseInt(digits[i]);
            } else {
                odds += parseInt(digits[i]);
            }
        }
        return (3 * odds) + evens;
    }
}
