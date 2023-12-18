/**
 * @author Brandon Cano
 * B11_Scoreboard
 */
public class Football implements Game {

    private static final String[] scoringMethods = new String[11];
    private static final int[] POINTS = {6, 3, 1, 2, 2, 6, 3, 1, 2, 2};
    private final Team homeTeam;
    private final Team visitingTeam;

    Football(Team homeTeam, Team visitingTeam) {
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
        return 4;
    }

    @Override
    public String getPeriodName() {
        return "quarter";
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
        scoringMethods[0] = home + " touchdown";
        scoringMethods[1] = home + " field goal";
        scoringMethods[2] = home + " extra-point";
        scoringMethods[3] = home + " two-point conversion";
        scoringMethods[4] = home + " safety";
        scoringMethods[5] = visiting + " touchdown";
        scoringMethods[6] = visiting + " field goal";
        scoringMethods[7] = visiting + " extra-point";
        scoringMethods[8] = visiting + " two-point conversion";
        scoringMethods[9] = visiting + " safety";
        scoringMethods[10] = "End quarter";
    }
}
