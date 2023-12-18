import java.util.HashMap;
import java.util.Map;

/**
 * @author Brandon Cano
 */
public class DecodeUPCA {

    private final Map<String, String> toDecimal = new HashMap<>();

    public DecodeUPCA() {
        String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] leftCodes = {"0001101", "0011001", "0010011", "0111101", "0100011", "0110001", "0101111", "0111011", "0110111", "0001011"};
        String[] rightCodes = {"1110010", "1100110", "1101100", "1000010", "1011100", "1001110", "1010000", "1000100", "1001000", "1110100"};

        for (int i = 0; i < 10; i++) {
            toDecimal.put(leftCodes[i], digits[i]);
            toDecimal.put(rightCodes[i], digits[i]);
        }
    }

    /**
     * This will take in some encoded product code and then decode it.
     *
     * @param productCode code as a String to be decoded
     * @return decoded product code
     */
    public String decode(String productCode) {
        productCode = productCode.substring(3, productCode.length() - 3); // strip 101 bars
        StringBuilder decimalCode = new StringBuilder();

        // first half
        for (int i = 0; i < 42; i += 7) {
            String decimal = toDecimal.get(productCode.substring(i, i + 7));
            decimalCode.append(decimal);
        }

        productCode = productCode.substring(47); // strip rest of first half
        for (int i = 0; i < 35; i += 7) {
            String decimal = toDecimal.get(productCode.substring(i, i + 7));
            decimalCode.append(decimal);
        }

        String checkDigit = productCode.substring(35);
        System.out.println("Check Digit - " + toDecimal.get(checkDigit));

        return decimalCode.toString();
    }
}
