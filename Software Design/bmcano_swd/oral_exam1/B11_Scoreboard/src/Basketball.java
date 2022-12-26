/**
 * @author Brandon Cano
 * B11_Scoreboard
 */
public class Basketball implements Game {

    private static final String[] scoringMethods = new String[7];
    private static final int[] POINTS = {2, 3, 1, 2, 3, 1};
    private final Team homeTeam;
    private final Team visitingTeam;

    Basketball(Team homeTeam, Team visitingTeam) {
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
        scoringMethods[0] = home + " 2 pointer";
        scoringMethods[1] = home + " 3 pointer";
        scoringMethods[2] = home + " free throw";
        scoringMethods[3] = visiting + " 2 pointer";
        scoringMethods[4] = visiting + " 3 pointer";
        scoringMethods[5] = visiting + " free throw";
        scoringMethods[6] = "End half";
    }
}
