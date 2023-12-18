package Screens;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.FlowLayout;

/**
 * @author Brandon Cano
 * A02_QuizGUI
 */
public class Acknowledgement extends ScreenFlow {

    private final JCheckBox checkBox;

    public Acknowledgement() {
        super("Acknowledgement");
        setLayout(new FlowLayout());

        JLabel acknowledgement = new JLabel();
        acknowledgement.setText("By checking this box, you verify that this is all your own work, and not anyone elses");
        add(acknowledgement);

        checkBox = new JCheckBox();
        add(checkBox);

        // submit button
        submitButton.setText("Submit Quiz");
        submitButton.setBounds(25, 120, submitButton.getWidth(), submitButton.getHeight());
        add(submitButton);

        submitButton.addActionListener(event -> {
            if(checkBox.isSelected()) {
                next = true;
            }
        });
    }
}
