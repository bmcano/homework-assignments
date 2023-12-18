import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * @author Brandon Cano
 * <p>
 * structure was inspired by the textbook example fig28_03_06
 */
public class Client extends JFrame {

    private final JTextField enterTextField;
    private final JTextArea displayArea;
    private final String chatServer;
    private Socket client;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private String message = "";

    public Client(String host) {
        super("Client");

        // set which server the client connects to
        chatServer = host;
        enterTextField = new JTextField();
        enterTextField.setEditable(false);
        enterTextField.setToolTipText("Enter text here.");

        // send message to server
        enterTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                sendData(event.getActionCommand());
                enterTextField.setText("");
            }
        });

        add(enterTextField, BorderLayout.NORTH);

        displayArea = new JTextArea();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        setSize(600, 300);
        setVisible(true);
    }

    /**
     * Will attempt to connect the client to the server and set up the streams for the IO.
     */
    public void runClient() {
        try {
            // connect to server
            displayMessage("Attempting to Connect");
            client = new Socket(InetAddress.getByName(chatServer), 55555); // port might need to be changed
            displayMessage("\nConnected to: " + client.getInetAddress().getHostName());

            // set up IO streams
            outputStream = new ObjectOutputStream(client.getOutputStream());
            outputStream.flush();

            inputStream = new ObjectInputStream(client.getInputStream());
            displayMessage("\nI/O streams created");

            processConnection();
        } catch (EOFException eofException) {
            displayMessage("\nConnection Terminated");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /**
     * This will take any message and process it so that it can be shown in the client side.
     *
     * @throws IOException for when the streams fail
     */
    private void processConnection() throws IOException {
        // allow user to send message
        setTextFieldEditable(true);

        do { // process message
            try {
                message = (String) inputStream.readObject();
                System.out.println(message);
                displayMessage(message);
            } catch (ClassNotFoundException classNotFoundException) {
                displayMessage("\nUnknown object.");
            }
        } while (!message.equals("\nSERVER >>> TERMINATE"));
    }

    /**
     * This will close the connection of the client.
     */
    private void closeConnection() {
        displayMessage("\nClosing connection");
        setTextFieldEditable(false);

        try {
            outputStream.close();
            inputStream.close();
            client.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * This will take the message entered in and send it to the server.
     *
     * @param message text to be sent to server
     */
    private void sendData(String message) {
        try {
            outputStream.writeObject(message);
            outputStream.flush(); // flush data to output
            displayMessage("\nCLIENT >>> " + message);
        } catch (IOException ioException) {
            displayArea.append("\nError writing object");
        }
    }

    /**
     * This will display any message that the user entered into the text area.
     *
     * @param messageToDisplay a String of the message the user entered
     */
    private void displayMessage(final String messageToDisplay) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                displayArea.append(messageToDisplay);
            }
        });
    }

    /**
     * This will either make the area to enter in text editable or not.
     *
     * @param editable boolean value to determine to edit the text area
     */
    private void setTextFieldEditable(final boolean editable) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                enterTextField.setEditable(editable);
            }
        });
    }
}
