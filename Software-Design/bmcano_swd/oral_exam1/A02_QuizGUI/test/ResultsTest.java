import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultsTest {

    @Test
    void emptyList() {
        List<String> answers = new ArrayList<>();

        int expectedResult = 0;
        int actualResult = Results.calculateScore(answers);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void allCorrectAnswers() {
        List<String> answers = new ArrayList<>();
        answers.add("Des Moines");
        answers.add("Python,Java,");
        answers.add("North America");
        answers.add("20");
        answers.add("Nebraska, Lincoln");

        int expectedResult = 5;
        int actualResult = Results.calculateScore(answers);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void someCorrectAndSomeIncorrectAnswers() {
        List<String> answers = new ArrayList<>();
        answers.add("Des Moines");
        answers.add("default");
        answers.add("North America");
        answers.add("10");
        answers.add("Nebraska, Lincoln");

        int expectedResult = 3;
        int actualResult = Results.calculateScore(answers);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void allIncorrectAnswers() {
        List<String> answers = new ArrayList<>();
        answers.add("Des");
        answers.add("default");
        answers.add("North");
        answers.add("10");
        answers.add("Nebraska");

        int expectedResult = 0;
        int actualResult = Results.calculateScore(answers);
        assertEquals(expectedResult, actualResult);
    }
}
