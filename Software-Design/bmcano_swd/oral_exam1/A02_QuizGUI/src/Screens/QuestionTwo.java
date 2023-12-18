package Screens;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

/**
 * @author Brandon Cano
 * A02_QuizGUI
 */
public class QuestionTwo extends ScreenFlow {

    private final JCheckBox[] group = new JCheckBox[4];

    public QuestionTwo()
    {
        super("Question 2");
        setLayout(null); // so we can set bounds with each component since it is a simple layout

        // JLabel constructor with a string argument
        JLabel question = new JLabel("2.) Which of these are programming languages");
        question.setToolTipText("Question 2");
        question.setBounds(10, 5, 490, 30);
        add(question);

        // the four multiple choice options
        JCheckBox button1 = new JCheckBox("Python");
        button1.setBounds(25, 30, 120, 20);
        add(button1);

        JCheckBox button2 = new JCheckBox("Java");
        button2.setBounds(25, 50, 120, 20);
        add(button2);

        JCheckBox button3 = new JCheckBox("C##");
        button3.setBounds(25, 70, 120, 20);
        add(button3);

        JCheckBox button4 = new JCheckBox("HTML");
        button4.setBounds(25, 90, 120, 20);
        add(button4);

        // save collection of the buttons
        group[0] = button1;
        group[1] = button2;
        group[2] = button3;
        group[3] = button4;

        // submit button
        submitButton.setBounds(25, 120, submitButton.getWidth(), submitButton.getHeight());
        add(submitButton);

        submitButton.addActionListener(event -> {
            System.out.println(getAnswer());
            if(!getAnswer().equals("")) {
                next = true;
            }
        });
    }

    @Override
    public String getAnswer() {
        StringBuilder answer = new StringBuilder();
        for (JCheckBox checkBox : group) {
            if (checkBox.isSelected()) {
                answer.append(checkBox.getText()).append(",");
            }
        }
        return answer.toString();
    }
}
