/**
 * @author Brandon Cano
 * B11_Scoreboard
 */
public class Team {
    private String name;
    private int score;

    Team(String name) {
        setName(name);
        score = 0;
    }

    public String getName() {
        return name;
    }

    /**
     * @param name is a string that the user chooses, if the string is empty a defaulted name is chosen
     */
    public void setName(String name) {
        if(name.equals("")) {
            this.name = "missing_name";
            return;
        }
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    /**
     * @param score is an integer that will add to the current score. Negative numbers are not allowed and will be
     *              defaulted to 0
     */
    public void addScore(int score) {
        if(score < 0) score = 0;
        this.score += score;
    }

    @Override
    public String toString() {
        return getName() + " - " + getScore();
    }
}
