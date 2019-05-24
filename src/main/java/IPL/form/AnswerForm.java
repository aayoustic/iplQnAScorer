package IPL.form;

/**
 * Created on 4/12/2019 6:54 PM.
 *
 * @author Aayush Shrivastava
 */
public class AnswerForm {
    private String matchWinner;
    private String[] highestRunGetter;
    private String[] highestWicketTaker;
    private String mom;
    private String runRange;
    private Bonus bonus;

    public String getMatchWinner() {
        return matchWinner;
    }

    public void setMatchWinner(String matchWinner) {
        this.matchWinner = matchWinner;
    }

    public String[] getHighestRunGetter() {
        return highestRunGetter;
    }

    public void setHighestRunGetter(String[] highestRunGetter) {
        this.highestRunGetter = highestRunGetter;
    }

    public String[] getHighestWicketTaker() {
        return highestWicketTaker;
    }

    public void setHighestWicketTaker(String[] highestWicketTaker) {
        this.highestWicketTaker = highestWicketTaker;
    }

    public String getMom() {
        return mom;
    }

    public void setMom(String mom) {
        this.mom = mom;
    }

    public String getRunRange() {
        return runRange;
    }

    public void setRunRange(String runRange) {
        this.runRange = runRange;
    }

    public Bonus getBonus() {
        return bonus;
    }

    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }
}
