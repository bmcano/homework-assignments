import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Brandon Cano
 * B11_Scoreboard
 */
public class GameTest {

    // baseball
    @Test
    void baseballTest() {
        Game game = new Baseball(new Team("Home"), new Team("Visiting"));

        assertEquals(13, game.getScoringMethods().length);
        assertEquals(9, game.getPeriodLength());
        assertEquals("innings", game.getPeriodName());
    }

    // basketball
    @Test
    void basketballTest() {
        Game game = new Basketball(new Team("Home"), new Team("Visiting"));

        assertEquals(7, game.getScoringMethods().length);
        assertEquals(2, game.getPeriodLength());
        assertEquals("half", game.getPeriodName());
    }

    // football
    @Test
    void footballTest() {
        Game game = new Football(new Team("Home"), new Team("Visiting"));

        assertEquals(11, game.getScoringMethods().length);
        assertEquals(4, game.getPeriodLength());
        assertEquals("quarter", game.getPeriodName());
    }

    // hockey
    @Test
    void hockeyTest() {
        Game game = new Hockey(new Team("Home"), new Team("Visiting"));

        assertEquals(5, game.getScoringMethods().length);
        assertEquals(3, game.getPeriodLength());
        assertEquals("period", game.getPeriodName());
    }

    // soccer
    @Test
    void soccerTest() {
        Game game = new Soccer(new Team("Home"), new Team("Visiting"));

        assertEquals(5, game.getScoringMethods().length);
        assertEquals(2, game.getPeriodLength());
        assertEquals("half", game.getPeriodName());
    }
}
