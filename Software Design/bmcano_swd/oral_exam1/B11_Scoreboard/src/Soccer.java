/**
 * @author Brandon Cano
 * B11_Scoreboard
 */
public class Soccer implements Game {
    private static final String[] scoringMethods = new String[5];
    private static final int[] POINTS = {1, 1, 1, 1};
    private final Team homeTeam;
    private final Team visitingTeam;

    Soccer(Team homeTeam, Team visitingTeam) {
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
        return 2;
    }

    @Override
    public String getPeriodName() {
        return "half";
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
        scoringMethods[1] = home + " penalty kick";
        scoringMethods[2] = visiting + " goal";
        scoringMethods[3] = visiting + " penalty kick";
        scoringMethods[4] = "End half";
    }
}
