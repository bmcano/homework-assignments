package Screens;

import javax.swing.JLabel;
import java.awt.FlowLayout;

/**
 * @author Brandon Cano
 * A02_QuizGUI
 */
public class HomeMenu extends ScreenFlow {

    public HomeMenu() {
        super("Quiz");
        setLayout(new FlowLayout());

        JLabel title = new JLabel();
        title.setText("Welcome to the quiz!");
        add(title);

        submitButton.setText("Start");
        submitButton.setBounds(25, 120, submitButton.getWidth(), submitButton.getHeight());
        add(submitButton);

        submitButton.addActionListener(event -> next = true);
    }

    public boolean goNext() {
        return next;
    }
}
