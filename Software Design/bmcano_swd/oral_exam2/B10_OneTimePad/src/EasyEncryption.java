/**
 * @author Brandon Cano
 * <p>
 * Easy problem, ignore this file
 */
public class EasyEncryption {

    final static char[] alphabet = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    public String encrypt(String message, int offset) {
        message = message.toUpperCase();
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            int index = findIndex(message.charAt(i));
            if (index == -1) continue; // skip character
            int encryptedIndex = (index + offset) % 26;
            encryptedMessage.append(alphabet[encryptedIndex]);
        }

        return encryptedMessage.toString();
    }

    public String decrypt(String message, int offset) {
        message = message.toUpperCase();
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            int index = findIndex(message.charAt(i));
            if (index == -1) continue; // skip character
            int encryptedIndex = (((index - offset) % 26) + 26) % 26;
            encryptedMessage.append(alphabet[encryptedIndex]);
        }

        return encryptedMessage.toString();
    }

    private int findIndex(char character) {
        int index = -1;
        for (int i = 0; i < alphabet.length; i++) {
            if (character == alphabet[i]) index = i;
        }
        return index;
    }
}
