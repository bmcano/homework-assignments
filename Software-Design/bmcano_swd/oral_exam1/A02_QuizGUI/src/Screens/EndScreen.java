package Screens;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;

/**
 * @author Brandon Cano
 * A02_QuizGUI
 */
public class EndScreen extends ScreenFlow {

    public EndScreen(int score) {
        super("Results");
        setLayout(new FlowLayout());

        JLabel title = new JLabel();
        title.setText("You got a score of " + score + "/5.");
        title.setFont(new Font("Serif", Font.PLAIN, 20));
        add(title);
    }
}
