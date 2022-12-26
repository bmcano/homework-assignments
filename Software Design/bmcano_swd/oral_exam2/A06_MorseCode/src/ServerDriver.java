import javax.swing.JFrame;

/**
 * @author Brandon Cano
 */
public class ServerDriver {

    public static void main(String[] args) {
        System.out.println("-- A06_MorseCode - Server --");

        Server serverApplication = new Server();
        serverApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        serverApplication.runServer();
    }
}
