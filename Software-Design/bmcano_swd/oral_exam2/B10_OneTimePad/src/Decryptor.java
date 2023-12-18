import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * @author Brandon Cano
 */
public class Decryptor {

    private final static char[] alphabet = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    /**
     * This will take in two locations to verify if they are correct for decrypting.
     *
     * @param keyLocation       current location of the key
     * @param encryptedLocation location where the encryption started from
     * @return the decrypted message if locations match file
     */
    public String decrypt(int keyLocation, int encryptedLocation) {
        String key = readKeyFile(keyLocation);
        String message = readEncryptedFile(encryptedLocation);

        if (key.equals("-1") || message.equals("-1")) {
            return "locations do not match";
        }

        StringBuilder decryptedMessage = new StringBuilder();
        String[] keyValues = key.split(",");

        for (int i = 0; i < message.length(); i++) {
            int index = Encryptor.findIndex(message.charAt(i));
            if (index == -1) continue; // skip character
            if (index == -2) { // special case for spaces
                decryptedMessage.append(" ");
            } else {
                String nValue = keyValues[i + encryptedLocation - 1]; // offset
                int decryptedIndex = (((index - parseInt(nValue)) % 26) + 26) % 26;
                decryptedMessage.append(alphabet[decryptedIndex]);
            }
        }
        return decryptedMessage.toString();
    }

    /**
     * This will read in the key file and test if the keys match in order to return the key.
     *
     * @param keyLocation location of the key
     * @return the key if location is verified
     */
    private String readKeyFile(int keyLocation) {
        String key = "";
        try {
            File keyFile = new File("oral_exam2/B10_OneTimePad/documents/key.txt");
            Scanner keyReader = new Scanner(keyFile);

            int location = parseInt(keyReader.nextLine());
            if (location != keyLocation) return "-1";

            key = keyReader.nextLine();
            keyReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: key.txt");
            e.printStackTrace();
        }

        return key;
    }

    /**
     * This will read in the encrypted file and test if the keys match in order to return the message.
     *
     * @param encryptedLocation location where encryption started
     * @return encrypted message
     */
    private String readEncryptedFile(int encryptedLocation) {
        String message = "";
        try {
            File encryptedFile = new File("oral_exam2/B10_OneTimePad/documents/encrypted.txt");
            Scanner encryptedReader = new Scanner(encryptedFile);

            int location = parseInt(encryptedReader.nextLine());
            if (location != encryptedLocation) return "-1";

            message = encryptedReader.nextLine();
            encryptedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: encrypted.txt");
            e.printStackTrace();
        }

        return message;
    }
}
