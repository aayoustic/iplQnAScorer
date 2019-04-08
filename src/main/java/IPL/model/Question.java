package IPL.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 4/8/2019 1:28 PM.
 *
 * @author Aayush Shrivastava
 */
public class Question {

    public enum QuestionType {
        MATCH_WINNER, HIGHEST_RUN_GETTER, HIGHEST_WICKET_TAKER, MOM, RUN_RANGE, BONUS
    }

    private static final Map<QuestionType, Integer> POINTS_MAP = new HashMap<>();
    static {
        POINTS_MAP.put(QuestionType.MATCH_WINNER, 10);
        POINTS_MAP.put(QuestionType.HIGHEST_RUN_GETTER, 20);
        POINTS_MAP.put(QuestionType.HIGHEST_WICKET_TAKER, 20);
        POINTS_MAP.put(QuestionType.MOM, 25);
        POINTS_MAP.put(QuestionType.RUN_RANGE, 15);
        POINTS_MAP.put(QuestionType.BONUS, 10);
    }

    public static Integer getPoints(QuestionType questionType){
        return POINTS_MAP.get(questionType);
    }
}
