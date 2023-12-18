import java.util.Scanner;

/**
 * @author Brandon Cano
 * A14_FourSquare
 */
public class FourSquareDriver {

    public static void main(String[] args) {
        System.out.println("-- A14_FourSquare --");

        // start of user interface
        Scanner selection = new Scanner(System.in);
        System.out.println("Type in your first keyword: ");
        String keywordOne = selection.nextLine();
        System.out.println("Type in your second keyword: ");
        String keywordTwo = selection.nextLine();

        Cipher cipher = new Cipher(keywordOne, keywordTwo);

        String choice;
        do {
            System.out.println("Would you like to encrypt or decrypt a message?\n1. Encrypt\n2. Decrypt\n3. Exit");
            choice = selection.nextLine();
            if (choice.equals("1")) {
                System.out.println("Type in your message to encrypt: ");
                String message = selection.nextLine();
                System.out.println(cipher.encrypt(message));
            } else if (choice.equals("2")) {
                System.out.println("Type in your message to decrypt: ");
                String message = selection.nextLine();
                System.out.println(cipher.decrypt(message));
            }
        } while (!choice.equals("3"));
    }
}
