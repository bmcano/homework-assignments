import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * @author Brandon Cano
 * B11_Scoreboard
 */
class MenuLogicTest {
    private static Team home;
    private static Team visiting;

    @BeforeAll
    public static void setup() {
        home = new Team("home");
        visiting = new Team("visiting");
    }

    // game selection
    @Test
    public void gameSelectionTest() {
        assertInstanceOf(Football.class, MenuLogic.createGame("1", home, visiting));
        assertInstanceOf(Basketball.class, MenuLogic.createGame("2", home, visiting));
        assertInstanceOf(Soccer.class, MenuLogic.createGame("3", home, visiting));
        assertInstanceOf(Hockey.class, MenuLogic.createGame("4", home, visiting));
        assertInstanceOf(Baseball.class, MenuLogic.createGame("5", home, visiting));
        assertInstanceOf(Football.class, MenuLogic.createGame("6", home, visiting));
    }

    @Test
    public void determineWinnerTest() {
        assertEquals("It's a tie!", MenuLogic.determineWinner(home, visiting));
        home.addScore(1);
        assertEquals("Winner: home", MenuLogic.determineWinner(home, visiting));
        visiting.addScore(2);
        assertEquals("Winner: visiting", MenuLogic.determineWinner(home, visiting));
    }
}