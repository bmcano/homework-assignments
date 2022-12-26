package Screens;

import java.awt.FlowLayout;
import java.util.Objects;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * @author Brandon Cano
 * A02_QuizGUI
 */
public class QuestionThree extends ScreenFlow {

    private final JComboBox<String> comboBox;

    public QuestionThree()
    {
        super("Question 3");
        setLayout(new FlowLayout()); // flowlayout works better with drop down boxes

        // JLabel constructor with a string argument
        JLabel question = new JLabel("3.) Which continent is the United States of America apart of?");
        question.setToolTipText("Question 3");
        add(question);

        // setup for combo box
        String[] dropDownChoices = {"-- not selected --","South America","North America","Europe","Asia"};
        comboBox = new JComboBox<>(dropDownChoices);
        add(comboBox);

        // submit button
        add(submitButton);

        submitButton.addActionListener(event -> {
            System.out.println(getAnswer());
            if(!getAnswer().equals("-- not selected --")) {
                next = true;
            }
        });
    }

    @Override
    public boolean goNext() {
        return next;
    }

    @Override
    public String getAnswer() {
        return Objects.requireNonNull(comboBox.getSelectedItem()).toString();
    }
}

