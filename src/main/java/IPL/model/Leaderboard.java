package IPL.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created on 4/8/2019 7:23 PM.
 *
 * @author Aayush Shrivastava
 */
@Entity
public class Leaderboard {

    @Id
    private String email;
    private String name;
    private int totalPoint;
    private int matchWinnerPoint;
    private int highestRunGetterPoint;
    private int highestWicketTakerPoint;
    private int momPoint;
    private int runRangePoint;
    private int bonusPoint;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(int totalPoint) {
        this.totalPoint = totalPoint;
    }

    public int getMatchWinnerPoint() {
        return matchWinnerPoint;
    }

    public void setMatchWinnerPoint(int matchWinnerPoint) {
        this.matchWinnerPoint = matchWinnerPoint;
    }

    public int getHighestRunGetterPoint() {
        return highestRunGetterPoint;
    }

    public void setHighestRunGetterPoint(int highestRunGetterPoint) {
        this.highestRunGetterPoint = highestRunGetterPoint;
    }

    public int getHighestWicketTakerPoint() {
        return highestWicketTakerPoint;
    }

    public void setHighestWicketTakerPoint(int highestWicketTakerPoint) {
        this.highestWicketTakerPoint = highestWicketTakerPoint;
    }

    public int getMomPoint() {
        return momPoint;
    }

    public void setMomPoint(int momPoint) {
        this.momPoint = momPoint;
    }

    public int getRunRangePoint() {
        return runRangePoint;
    }

    public void setRunRangePoint(int runRangePoint) {
        this.runRangePoint = runRangePoint;
    }

    public int getBonusPoint() {
        return bonusPoint;
    }

    public void setBonusPoint(int bonusPoint) {
        this.bonusPoint = bonusPoint;
    }
}