package Screens;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author Brandon Cano
 * A02_QuizGUI
 */
public abstract class ScreenFlow extends JFrame {
    protected Boolean next = false;
    protected final JButton submitButton;

    ScreenFlow(String title) {
        super(title);
        submitButton = new JButton();
        submitButton.setText("Next Questions.");
        submitButton.setSize(150,50);
    }

    /**
     * @return a string of the answer(s) that is selected for each question, if any
     */
    public String getAnswer() {
        return null;
    }

    /**
     * @return a boolean value that determines if the program should progress to the next question
     */
    public boolean goNext() {
        return next;
    }
}
