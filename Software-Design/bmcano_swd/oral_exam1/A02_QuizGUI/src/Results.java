import java.util.List;

/**
 * @author Brandon Cano
 * A02_QuizGUI
 */
public class Results {

    private static final String[] ANSWERS = {"Des Moines","Python,Java,","North America","20","Nebraska, Lincoln"};

    /**
     * @param userAnswers is a list of the answers retrieved by the user
     * @return the amount of correct answers out of 5
     */
    public static int calculateScore(List<String> userAnswers) {
        int score = 0;
        for (int i=0; i<userAnswers.size(); i++) {
            if(userAnswers.get(i).equals(ANSWERS[i])) {
                score++;
            }
        }
        return score;
    }
}
