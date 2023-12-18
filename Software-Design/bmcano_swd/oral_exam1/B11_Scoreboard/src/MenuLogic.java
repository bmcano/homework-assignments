/**
 * @author Brandon Cano
 * B11_Scoreboard
 */
public class MenuLogic {

    /**
     * @param selection user selection specifying their game choice
     * @param homeTeam the object of the home team
     * @param visitingTeam the object of the visiting team
     * @return a new object of the sport that has been selected
     */
    public static Game createGame(String selection, Team homeTeam, Team visitingTeam) {
        return switch (selection) {
            case "2" -> new Basketball(homeTeam, visitingTeam);
            case "3" -> new Soccer(homeTeam, visitingTeam);
            case "4" -> new Hockey(homeTeam, visitingTeam);
            case "5" -> new Baseball(homeTeam, visitingTeam);
            default -> new Football(homeTeam, visitingTeam); // defaulted case is chosen to be football
        };
    }

    /**
     * @param game current instance of the game
     * @param homeTeam the information of the home team
     * @param visitingTeam the information of the visiting team
     * @param index the index to get the correct points and add them to the correct team
     */
    public static void updateScores(Game game, Team homeTeam, Team visitingTeam, int index) {
        if(index <= (game.getScoringMethods().length-1) / 2) {
            homeTeam.addScore(game.getPoints(index-1));
        } else {
            visitingTeam.addScore(game.getPoints(index-1));
        }
    }

    /**
     * @param homeTeam the information of the home team
     * @param visitingTeam the information of the visiting team
     * @return a String specifying the winner with their team name
     */
    public static String determineWinner(Team homeTeam, Team visitingTeam) {
        String winner = "Winner: ";
        if (homeTeam.getScore() > visitingTeam.getScore()) {
            winner += homeTeam.getName();
        } else if (homeTeam.getScore() < visitingTeam.getScore()) {
            winner += visitingTeam.getName();
        } else {
            winner = "It's a tie!";
        }
        return winner;
    }

    /**
     * @param game array of the scoring methods to be printed
     */
    public static void printScoringMethods(String[] game) {
        for (int i=0; i<game.length; i++) {
            System.out.println((i+1) + ". " + game[i]);
        }
    }
}
