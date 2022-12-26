import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * @author Brandon Cano
 */
public class Encryptor {

    private final static char[] alphabet = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    /**
     * This will find the index value of a letter from the alphabet.
     *
     * @param character value to find the index of
     * @return the index of the specified character
     */
    public static int findIndex(char character) {
        int index = -1;
        for (int i = 0; i < alphabet.length; i++) {
            if (character == alphabet[i]) index = i;
            if (character == ' ') index = -2;
        }
        return index;
    }

    /**
     * This will take in a String and an integer location and encrypt the message from the key file.
     *
     * @param location starting point to encrypt from
     * @param message  item to encrypt
     * @return encrypted message
     */
    public String encrypt(int location, String message) {
        message = message.toUpperCase();
        // read from key file and convert to array
        String key = readKeyFile();
        String[] keyValues = key.split(",");

        // encrypt the message
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            int index = findIndex(message.charAt(i));
            if (index == -1) continue; // skip character
            if (index == -2) { // special case for spaces
                encryptedMessage.append(" ");
            } else {
                String nValue = keyValues[i + location - 1]; // offset
                int encryptedIndex = (index + parseInt(nValue)) % 26;
                encryptedMessage.append(alphabet[encryptedIndex]);
            }
        }

        // write 'location' and 'encryptedMessage' to encrypted file
        writeEncryptedFile(location, encryptedMessage.toString());

        // update location in key file
        int newLocation = updatedLocation(location, message);
        updateKeyFileLocation(newLocation);
        return encryptedMessage.toString();
    }

    /**
     * This will read the key file and then return the key.
     *
     * @return the key as a String
     */
    private String readKeyFile() {
        String key = "";
        try {
            File keyFile = new File("oral_exam2/B10_OneTimePad/documents/key.txt");
            Scanner keyReader = new Scanner(keyFile);

            // we know that there are only two lines to read, the location and the key, ignore location
            keyReader.nextLine();
            key = keyReader.nextLine();
            keyReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: key.txt");
            e.printStackTrace();
        }

        return key;
    }

    /**
     * This will create and then write the encrypted file, with the starting location for encrypting.
     *
     * @param location the location of the starting point
     * @param message  the encrypted message
     */
    private void writeEncryptedFile(int location, String message) {
        try {
            // create file
            File file = new File("oral_exam2/B10_OneTimePad/documents/encrypted.txt");
            if (file.createNewFile()) System.out.println("File created: " + file.getName());

            // write to file
            FileWriter myWriter = new FileWriter("oral_exam2/B10_OneTimePad/documents/encrypted.txt");
            myWriter.write(location + "\n" + message.strip());
            myWriter.close();
            System.out.println("Encrypted location: " + location);
        } catch (IOException e) {
            System.out.println("Error with: encrypted.txt");
            e.printStackTrace();
        }
    }

    /**
     * This will take the key file and update the starting location of the key after encrypting.
     *
     * @param newLocation the new starting point for the key
     */
    private void updateKeyFileLocation(int newLocation) {
        try {
            File keyFile = new File("oral_exam2/B10_OneTimePad/documents/key.txt");
            Scanner keyReader = new Scanner(keyFile);

            // we will know that there are only two lines to read, the location and the key
            keyReader.nextLine();
            String key = keyReader.nextLine();
            keyReader.close();

            // since file is small rewriting will be the easiest choice
            FileWriter keyWriter = new FileWriter("oral_exam2/B10_OneTimePad/documents/key.txt");
            keyWriter.write(newLocation + "\n");
            keyWriter.write(key);
            keyWriter.close();
            System.out.println("Key location: " + newLocation);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: key.txt");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error with: key.txt");
            e.printStackTrace();
        }
    }

    /**
     * This will take the message the user wanted to encrypt and add it to the current location to update to the file.
     *
     * @param location current location of the key
     * @param message  message to find the length of
     * @return the new location of the key
     */
    private int updatedLocation(int location, String message) {
        String m = message.replace(" ", "");
        return location + m.length();
    }
}
