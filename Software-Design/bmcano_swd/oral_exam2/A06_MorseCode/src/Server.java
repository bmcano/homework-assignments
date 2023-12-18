import java.awt.BorderLayout;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * @author Brandon Cano
 * <p>
 * structure was inspired by the textbook example fig28_03_06
 */
public class Server extends JFrame {

    private final JTextArea displayArea;
    private Socket connection;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public Server() {
        super("Server");

        displayArea = new JTextArea();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        setSize(600, 300);
        setVisible(true);
    }

    /**
     * Will attempt to find a connection and will set up the IO streams.
     */
    public void runServer() {
        try {
            ServerSocket server = new ServerSocket(55555, 100); // might need to change port
            while (true) {
                try {
                    // wait for connection
                    displayMessage("Waiting for connection");
                    connection = server.accept();
                    displayMessage("\nConnection received from: " + connection.getInetAddress().getHostName());

                    // get IO streams
                    outputStream = new ObjectOutputStream(connection.getOutputStream());
                    outputStream.flush();

                    inputStream = new ObjectInputStream(connection.getInputStream());
                    displayMessage("\nI/O streams received");

                    processConnection();
                } catch (EOFException eofException) {
                    displayMessage("\nServer terminated connection");
                } finally {
                    closeConnection();
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * This will take the message from the client to process the information.
     *
     * @throws IOException for when the streams fail
     */
    private void processConnection() throws IOException {
        String message = "Connection successful";
        sendData(message);
        do {
            try {
                message = (String) inputStream.readObject(); // this is the message received from the client
                displayMessage("\n" + message);
                if (convertToMorseCode(message)) {
                    sendData(Converter.toMorseCode(message));
                } else {
                    sendData(Converter.toEnglish(message));
                }
            } catch (ClassNotFoundException classNotFoundException) {
                displayMessage("\nUnknown object type received");
            }
        } while (!message.equals("CLIENT >>> TERMINATE"));
    }

    /**
     * This will close the connection of the server.
     */
    private void closeConnection() {
        displayMessage("\nTerminating connection\n");

        try {
            outputStream.close();
            inputStream.close();
            connection.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * This will take the message entered in and send it to the client.
     *
     * @param message text to be sent to client
     */
    private void sendData(String message) {
        try {
            outputStream.writeObject("\nSERVER >>> " + message);
            outputStream.flush(); // flush output to client
            displayMessage("\nSERVER >>> " + message);
        } catch (IOException ioException) {
            displayArea.append("\nError writing object");
        }
    }

    /**
     * This will display any message that the user entered into the text area.
     *
     * @param message a String of the message the user entered
     */
    private void displayMessage(final String message) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                displayArea.append(message);
            }
        });
    }

    /**
     * Determines if the message should be converted to morse code or not.
     *
     * @param message message received from client
     * @return boolean value
     */
    private boolean convertToMorseCode(String message) {
        // RegEx will find any sort of letter or number
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(message);
        return matcher.find();
    }
}
