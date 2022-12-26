/**
 * @author Brandon Cano
 * B11_Scoreboard
 */
public interface Game {

    /**
     * @return an array of all the different scoring methods/plays that could occur
     * for the specific game
     */
    String[] getScoringMethods();

    /**
     * @return the length of the period with respect to the current sport
     */
    int getPeriodLength();

    /**
     * @return the name of the period with respect to the current sport
     */
    String getPeriodName();

    /**
     * @param index is an integer of the index depicted by the user's selection
     * @return the amount of points for a certain play being chosen
     */
    int getPoints(int index);
}
