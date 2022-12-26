import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Brandon Cano
 * B11_Scoreboard
 */
public class ScoreboardDriver {

    public static void main(String[] args) {
        System.out.println("-- B11_Scoreboard --\n");

        // Menu starts here
        Scanner selection = new Scanner(System.in);
        System.out.println("Select the type of game:");
        System.out.println("1. Football");
        System.out.println("2. Basketball");
        System.out.println("3. Soccer");
        System.out.println("4. Hockey");
        System.out.println("5. Baseball");
        // enter game choice
        String gameChoice = selection.nextLine();
        // enter teams
        System.out.println("Enter home team:");
        Team homeTeam = new Team(selection.nextLine());
        System.out.println("Enter visiting team");
        Team visitingTeam = new Team(selection.nextLine());

        // create game
        Game game = MenuLogic.createGame(gameChoice, homeTeam, visitingTeam);

        // start game
        int period = 1;
        List<String> replayOfGame = new ArrayList<>();
        while (true) {
            MenuLogic.printScoringMethods(game.getScoringMethods());

            String choice = selection.nextLine();
            if (choice.equals("" + game.getScoringMethods().length)) {
                replayOfGame.add("End of " + game.getPeriodName() + " " + period);
                period++;
                if(period > game.getPeriodLength()) break; // end game
            } else { // adds the points to the correct team based on user selection
                int index = Integer.parseInt(choice);
                replayOfGame.add(game.getScoringMethods()[index-1]);
                MenuLogic.updateScores(game, homeTeam, visitingTeam, index);
            }

            // print updated score and current period
            System.out.println(homeTeam + ", " + visitingTeam);
            System.out.println("Current " + game.getPeriodName() + ": " + period + "\n");
        }

        // post game
        System.out.println("Game is over.");
        System.out.println(homeTeam + ", " + visitingTeam);

        // determine winner
        String winner = MenuLogic.determineWinner(homeTeam, visitingTeam);
        System.out.println(winner);

        // ask for replay option
        System.out.println("Would you like to see a replay of the game?:\n1. Yes\n2. No");
        String replay = selection.nextLine();
        if(replay.equals("1")) {
            for (String play : replayOfGame) {
                System.out.println(play);
            }
        } // otherwise exit
    }
}
