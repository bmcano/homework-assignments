/**
 * @author Brandon Cano
 * B11_Scoreboard
 */
public class Hockey implements Game {

    private static final String[] scoringMethods = new String[5];
    private static final int[] POINTS = {1, 2, 1, 2};
    private final Team homeTeam;
    private final Team visitingTeam;

    Hockey(Team homeTeam, Team visitingTeam) {
        this.homeTeam = homeTeam;
        this.visitingTeam = visitingTeam;
        createScoringOptions();
    }

    @Override
    public String[] getScoringMethods() {
        return scoringMethods;
    }

    @Override
    public int getPeriodLength() {
        return 3;
    }

    @Override
    public String getPeriodName() {
        return "period";
    }

    @Override
    public int getPoints(int index) {
        return POINTS[index];
    }

    /**
     * private helper class that initializes the scoringMethods array with the team names added in
     */
    private void createScoringOptions() {
        String home = homeTeam.getName();
        String visiting = visitingTeam.getName();
        scoringMethods[0] = home + " goal";
        scoringMethods[1] = home + " goal with assist";
        scoringMethods[2] = visiting + " goal";
        scoringMethods[3] = visiting + " goal with assist";
        scoringMethods[4] = "End period";
    }
}
