package Screens;

import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 * @author Brandon Cano
 * A02_QuizGUI
 */
public class QuestionOne extends ScreenFlow {

    private final ButtonGroup group;

    public QuestionOne()
    {
        super("Question 1");
        setLayout(null); // so we can set bounds with each component since it is a simple layout

        // JLabel constructor with a string argument
        JLabel question = new JLabel("1.)  What is the capital of Iowa");
        question.setToolTipText("Questions 1");
        question.setBounds(10, 5, 360, 30);
        add(question);

        // the four multiple choice options
        JRadioButton button1 = new JRadioButton("Des Moines");
        button1.setBounds(25, 30, 120, 20);
        add(button1);

        JRadioButton button2 = new JRadioButton("Iowa City");
        button2.setBounds(25, 50, 120, 20);
        add(button2);

        JRadioButton button3 = new JRadioButton("Cedar Rapids");
        button3.setBounds(25, 70, 120, 20);
        add(button3);

        JRadioButton button4 = new JRadioButton("Cedar Falls");
        button4.setBounds(25, 90, 120, 20);
        add(button4);

        // allows only one button to be selected at a time
        group = new ButtonGroup();
        group.add(button1);
        group.add(button2);
        group.add(button3);
        group.add(button4);

        // submit button
        submitButton.setBounds(25, 120, submitButton.getWidth(), submitButton.getHeight());
        add(submitButton);

        submitButton.addActionListener(event -> {
            System.out.println(getAnswer());
            if(getAnswer() != null) {
                next = true;
            }
        });
    }

    @Override
    public String getAnswer() {
        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
}
