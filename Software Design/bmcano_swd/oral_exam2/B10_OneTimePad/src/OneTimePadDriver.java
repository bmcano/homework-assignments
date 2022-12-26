import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * @author Brandon Cano
 */
public class OneTimePadDriver {

    public static void main(String[] args) {
        System.out.println("-- B10_OneTimePad --");

        // generate key
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of values to have for encrypting.");
        String nValues = input.next();

        System.out.println("Enter the location to start from.");
        String location = input.next();

        KeyGenerator key = new KeyGenerator(parseInt(location));
        key.generateKey(parseInt(nValues));

        // encrypt
        Scanner inputMessage = new Scanner(System.in);
        System.out.println("Enter the the message to encrypt.");
        String message = inputMessage.nextLine();

        Encryptor encryptor = new Encryptor();
        String m = encryptor.encrypt(parseInt(location), message);
        System.out.println(m);

        // decrypt
        Scanner inputLocation = new Scanner(System.in);
        System.out.println("Enter the location of the key");
        int keyLocation = parseInt(inputLocation.next());
        System.out.println("Enter the location of the encrypted message");
        int encryptionLocation = parseInt(inputLocation.next());

        Decryptor decryptor = new Decryptor();
        String d = decryptor.decrypt(keyLocation, encryptionLocation);
        System.out.println(d);
    }
}
