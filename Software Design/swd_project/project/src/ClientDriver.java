import javax.swing.*;

public class ClientDriver {

    public static void main(String[] args) {
        String serverIP = "172.17.0.220"; // IP of Brandon's computer on Eduroam, do not change
        String localhost = "127.0.0.1"; // localhost
        Client application = new Client(localhost);

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setSize(800, 600);
        application.setVisible(true);
        application.setResizable(false);
        application.setLocationRelativeTo(null);
        application.runClient();
    }
}
