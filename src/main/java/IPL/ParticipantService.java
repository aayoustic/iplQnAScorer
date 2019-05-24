package IPL;

import IPL.constant.IPLConstants;
import IPL.model.Participant;
import IPL.model.Question;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Created on 4/19/2019 1:57 AM.
 *
 * @author Aayush Shrivastava
 */
public class ParticipantService {

    private static final Logger LOGGER = Logger.getLogger(ParticipantService.class.getName());

    public static int calculateBonusPoints(Map<Question.QuestionType, Integer> points){
        int totalBonusPoints = 0;
        for (Question.QuestionType questionType : points.keySet()) {
            if(questionType instanceof Question.BonusQuestions &&
                    ((Question.BonusQuestions) questionType).name().startsWith(IPLConstants.BONUS_PREFIX)){
                LOGGER.info("questionType : " + questionType);
                totalBonusPoints += points.get(questionType);
            }
        }
        LOGGER.info("totalBonusPoints : " + totalBonusPoints);
        return totalBonusPoints;
    }

    public static String[] getCalculatedCSVRow(Participant participant){
        return new String[]{
                participant.getName(),
                String.valueOf(participant.getTotalPoints()),
                String.valueOf(participant.getPoints().get(Question.StaticQuestions.MATCH_WINNER)),
                String.valueOf(participant.getPoints().get(Question.StaticQuestions.HIGHEST_RUN_GETTER)),
                String.valueOf(participant.getPoints().get(Question.StaticQuestions.HIGHEST_WICKET_TAKER)),
                String.valueOf(participant.getPoints().get(Question.StaticQuestions.MOM)),
                String.valueOf(participant.getPoints().get(Question.StaticQuestions.RUN_RANGE)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS1)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS2)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS3)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS4)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS5)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS6)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS7)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS8)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS9)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS10)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS11)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS12)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS13)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS14)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS15)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS16)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS17)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS18)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS19)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS20)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS21)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS22)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS23)),
                String.valueOf(participant.getPoints().get(Question.BonusQuestions.BONUS24))
        };
    }
}
