import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * @author Brandon Cano
 */
public class EncodePOSTNET {

    private final Map<String, String> toBinary = new HashMap<>();

    public EncodePOSTNET() {
        String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] codes = {"11000", "00011", "00101", "00110", "01001", "01010", "01100", "10001", "10010", "10100"};

        for (int i = 0; i < 10; i++) {
            toBinary.put(digits[i], codes[i]);
        }
    }

    /**
     * This method will take in a zip code of either 5, 9, or 11 digits, and will then convert
     * it to a binary representation and return it, along with printing the visual representation.
     * Zip: 5-digit, Zip+4: 9-digit, Zip+4+Delivery: 11-digit
     *
     * @param zipCode a string of the decimal representation of the zip code
     * @return a string of the zip code in a binary representation
     */
    public String encode(String zipCode) {
        StringBuilder binaryZip = new StringBuilder();
        String[] zip = zipCode.split("");
        for (String digit : zip) {
            binaryZip.append(toBinary.get(digit));
        }
        binaryZip.append(toBinary.get(checkSum(zipCode)));
        System.out.println("|" +
                binaryZip.toString().replace("1", "|").replace("0", ".") + "|");
        return "1" + binaryZip + "1";
    }

    /**
     * This is a private helper method so that the encoding method will be able to find the last digit of the barcode
     *
     * @param zipCode a string that has the zipCode
     * @return the checkSum operation to add the final number to the barcode
     */
    private String checkSum(String zipCode) {
        int sum = zipSum(zipCode);
        if (sum % 10 != 0) return String.valueOf(10 - (sum % 10));
        return "0";
    }

    /**
     * This is a private helper method which will sum the digits in a zip code so that checkSum can be called
     *
     * @param zipCode a String of the decimal zip code
     * @return the sum of the digits in the zip code
     */
    private int zipSum(String zipCode) {
        String[] zip = zipCode.split("");
        int sum = 0;
        for (String i : zip) {
            sum += parseInt(i);
        }
        return sum;
    }
}
