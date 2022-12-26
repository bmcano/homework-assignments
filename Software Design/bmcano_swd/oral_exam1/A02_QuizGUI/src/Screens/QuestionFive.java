package Screens;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.FlowLayout;

/**
 * @author Brandon Cano
 * A02_QuizGUI
 */
public class QuestionFive extends ScreenFlow {

    private final JList<String> list1;
    private final JList<String> list2;
    private static final String[] STATES = {"Iowa", "Illinois", "Ohio", "Indiana", "Nebraska"};
    private static final String[] CAPITALS = {"Albany", "Boston", "Lincoln", "Austin", "Baton Rouge"};

    public QuestionFive() {
        super("Question 5");
        setLayout(new FlowLayout());

        JLabel question = new JLabel("Find the singular correct match of states to their capitols");
        question.setToolTipText("Questions 5");
        question.setBounds(10, 5, 360, 30);
        add(question);

        list1 = new JList<>(STATES);
        list1.setVisibleRowCount(5);
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(list1));

        list2 = new JList<>(CAPITALS);
        list2.setVisibleRowCount(5);
        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(list2));

        // submit button
        submitButton.setBounds(25, 120, submitButton.getWidth(), submitButton.getHeight());
        add(submitButton);

        submitButton.addActionListener(event -> {
            System.out.println(getAnswer());
            if(!getAnswer().contains("null")) {
                next = true;
            }
        });
    }

    @Override
    public String getAnswer() {
        return list1.getSelectedValue() + ", " + list2.getSelectedValue();
    }
}
