import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Brandon Cano
 * B11_Scoreboard
 */
class TeamTest {

    @Test
    public void constructorTest() {
        Assertions.assertThrows(NullPointerException.class, () -> new Team(null));

        Team team1 = new Team("");
        assertEquals("missing_name", team1.getName());
        assertEquals(0, team1.getScore());

        Team team2 = new Team("test name");
        assertEquals("test name", team2.getName());
        assertEquals(0, team1.getScore());
    }

    @Test
    public void nameTest() {
        Team team = new Team("");
        assertEquals("missing_name", team.getName());

        team.setName("new name");
        assertEquals("new name", team.getName());

        team.setName("A Different Name");
        assertEquals("A Different Name", team.getName());

        team.setName(" ");
        assertEquals(" ", team.getName());

        team.setName("");
        assertEquals("missing_name", team.getName());
    }

    @Test
    public void scoreTest() {
        Team team = new Team("name");

        assertEquals(0, team.getScore());

        team.addScore(-3); // negative number are not allowed
        assertEquals(0, team.getScore());

        team.addScore(3);
        assertEquals(3, team.getScore());

        team.addScore(6);
        assertEquals(9, team.getScore());

        team.addScore(3);
        assertEquals(12, team.getScore());

        team.addScore(-1);
        assertEquals(12, team.getScore());
    }

    @Test
    public void toStringTest() {
        Team team = new Team("name");
        team.addScore(5);
        assertEquals("name - 5", team.toString());
    }
}