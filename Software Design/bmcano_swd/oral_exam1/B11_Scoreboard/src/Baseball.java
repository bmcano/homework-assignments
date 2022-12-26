/**
 * @author Brandon Cano
 * B11_Scoreboard
 */
public class Baseball implements Game {

    private static final String[] scoringMethods = new String[13];
    private static final int[] POINTS = {1, 0, 0, 0, 1, 4, 1, 0, 0, 0, 1, 4};
    private final Team homeTeam;
    private final Team visitingTeam;

    Baseball(Team homeTeam, Team visitingTeam) {
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
        return 9;
    }

    @Override
    public String getPeriodName() {
        return "innings";
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
        scoringMethods[0] = home + " run";
        scoringMethods[1] = home + " single";
        scoringMethods[2] = home + " double";
        scoringMethods[3] = home + " triple";
        scoringMethods[4] = home + " home run";
        scoringMethods[5] = home + " grand slam";
        scoringMethods[6] = visiting + " run";
        scoringMethods[7] = visiting + " single";
        scoringMethods[8] = visiting + " double";
        scoringMethods[9] = visiting + " triple";
        scoringMethods[10] = visiting + " home run";
        scoringMethods[11] = visiting + " grand slam";
        scoringMethods[12] = "Next inning";
    }
}
