import java.util.HashMap;
import java.util.Map;

/**
 * @author Brandon Cano
 */
public class DecodePOSTNET {

    private final Map<String, String> toDecimal = new HashMap<>();

    public DecodePOSTNET() {
        String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] codes = {"11000", "00011", "00101", "00110", "01001", "01010", "01100", "10001", "10010", "10100"};

        for (int i = 0; i < 10; i++) {
            toDecimal.put(codes[i], digits[i]);
        }
    }

    /**
     * This method will take in a zip code barcode, and will then convert it back to the decimal representation and
     * return it.
     *
     * @param zipCode a string of the decimal representation of the zip code
     * @return a string of the zip code in a binary representation
     */
    public String decode(String zipCode) {
        zipCode = zipCode.substring(1, zipCode.length() - 1); // strip frame bars
        StringBuilder decimalZip = new StringBuilder();
        for (int i = 5; i < zipCode.length(); i += 5) {
            decimalZip.append(toDecimal.get(zipCode.substring(i - 5, i)));
        }
        return decimalZip.toString();
    }
}
