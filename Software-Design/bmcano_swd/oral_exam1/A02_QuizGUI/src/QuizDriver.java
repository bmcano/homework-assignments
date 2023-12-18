import Screens.*;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Brandon Cano
 * A02_QuizGUI
 */
public class QuizDriver {
    public static void main(String[] args) {
        System.out.println("A02_QuizGUI");

        // setup for home menu
        ScreenFlow home = new HomeMenu();
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setBounds(100, 100, 200, 100);

        // setup for each question
        ScreenFlow questionOne = new QuestionOne();
        questionOne.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        questionOne.setBounds(100, 100, 250, 250);

        ScreenFlow questionTwo = new QuestionTwo();
        questionTwo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        questionTwo.setBounds(100, 100, 350, 250);

        ScreenFlow questionThree = new QuestionThree();
        questionThree.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        questionThree.setBounds(100, 100, 510, 150);

        ScreenFlow questionFour = new QuestionFour();
        questionFour.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        questionFour.setBounds(100, 100, 300, 250);

        ScreenFlow questionFive = new QuestionFive();
        questionFive.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        questionFive.setBounds(100, 100, 450, 200);

        // setup for acknowledgement
        ScreenFlow acknowledgement = new Acknowledgement();
        acknowledgement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        acknowledgement.setBounds(100, 100, 625, 150);

        // array of question layouts
        ScreenFlow[] questions = {home, questionOne, questionTwo, questionThree, questionFour, questionFive, acknowledgement};
        List<String> userAnswers = new ArrayList<>(); // the answers the user selected

        // quiz flow
        ScreenFlow question;
        int index=0;
        while(index < questions.length) {
            question = questions[index];
            question.setVisible(true);
            if(question.goNext()) {
                question.setVisible(false);
                question.dispose();
                if(question.getAnswer() != null) {
                    userAnswers.add(question.getAnswer());
                }
                index++;
            }
        }

        ScreenFlow end = new EndScreen(Results.calculateScore(userAnswers));
        end.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        end.setBounds(150, 100, 300, 150);
        end.setVisible(true);
    }
}
