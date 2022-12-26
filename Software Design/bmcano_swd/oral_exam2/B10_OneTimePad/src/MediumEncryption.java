import java.util.Random;

import static java.lang.Integer.parseInt;

/**
 * @author Brandon Cano
 * <p>
 * Medium problem, ignore this file
 */
public class MediumEncryption {

    final static char[] alphabet = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    private String message = "";

    public String encrypt(String message) {
        Random random = new Random();
        StringBuilder encryptedMessage = new StringBuilder();
        StringBuilder key = new StringBuilder();

        message = message.toUpperCase();

        for (int i = 0; i < message.length(); i++) {
            int index = findIndex(message.charAt(i));
            if (index == -1) continue; // skip character

            int offset = random.nextInt(26);
            int encryptedIndex = (index + offset) % 26;
            encryptedMessage.append(alphabet[encryptedIndex]);

            // key formatting to print
            if (i == message.length() - 1) key.append(offset);
            else key.append(offset).append(",");
        }

        System.out.println("Key - " + key);
        this.message = encryptedMessage.toString();

        return encryptedMessage.toString();
    }

    public String decrypt(String key) {
        StringBuilder decryptedMessage = new StringBuilder();
        String[] offsets = key.split(",");

        for (int i = 0; i < message.length(); i++) {
            int index = findIndex(message.charAt(i));
            if (index == -1) continue; // skip character

            int decryptedIndex = (((index - parseInt(offsets[i])) % 26) + 26) % 26;
            decryptedMessage.append(alphabet[decryptedIndex]);
        }

        return decryptedMessage.toString();
    }

    private int findIndex(char character) {
        int index = -1;
        for (int i = 0; i < alphabet.length; i++) {
            if (character == alphabet[i]) index = i;
        }
        return index;
    }
}
