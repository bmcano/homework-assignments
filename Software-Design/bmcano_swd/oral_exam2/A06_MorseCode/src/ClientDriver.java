import javax.swing.JFrame;

/**
 * @author Brandon Cano
 */
public class ClientDriver {

    public static void main(String[] args) {
        System.out.println("-- A06_MorseCode - Client --");

        Client clientApplication;
        if (args.length == 0) clientApplication = new Client("127.0.0.1");
        else clientApplication = new Client(args[0]);

        clientApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientApplication.runClient();
    }
}
