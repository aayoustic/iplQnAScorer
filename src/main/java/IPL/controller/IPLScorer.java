package IPL.controller;

import IPL.constant.IPLConstants;
import IPL.model.*;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created on 4/8/2019 11:35 AM.
 *
 * @author Aayush Shrivastava
 */
@Controller
@RequestMapping("/ipl")
public class IPLScorer {

    private static final Logger LOGGER = Logger.getLogger(IPLScorer.class.getName());

    @Autowired private LeaderboardRepository leaderboardRepository;
    private static List<Participant> participants = new ArrayList<>();
    private static final String INPUT_PREDICTION_FILE_NAME = "Input/Test.csv";
    private static final String POINTS_OUTPUT_FILE_NAME = "Output/Output2.csv";
    private static final String[] POINTS_OUTPUT_CSV_COLUMNS = new String[]{
            IPLConstants.DATE,
            IPLConstants.NAME,
            IPLConstants.TOTAL_POINTS,
            IPLConstants.MATCH_WINNER,
            IPLConstants.RUNS,
            IPLConstants.WICKETS,
            IPLConstants.MOM,
            IPLConstants.RUN_RANGE,
            IPLConstants.BONUS
    };

    @RequestMapping(value = "/calculateScore", method = RequestMethod.POST)
    public void calculateScore(@RequestBody Answer answer) {
        LOGGER.info("Calculation started.");
        answer.set();
        participants = getParticipants();
        LOGGER.info("Participants count = " + participants.size());
        calculatePoints();
        writeToCSV();
        saveToDB();
        LOGGER.info("Calculation done.");
    }

    private static List<Participant> getParticipants(){
        List<Participant> participants = new ArrayList<>();
        String line;
        boolean isColumnRow = true;
        try(BufferedReader br = new BufferedReader(new FileReader(IPLConstants.CSV_PATH + INPUT_PREDICTION_FILE_NAME))) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(IPLConstants.SPREAD_SHEET_TIMESTAMP_PATTERN);
            while ((line = br.readLine()) != null) {
                if(isColumnRow) {
                    isColumnRow = false;
                    continue;
                }
                String[] row = line.split(IPLConstants.COMMA_DELIMITER);
                int rowCount = 0;
                Participant participant = new Participant();
                Date fillUpTime = simpleDateFormat.parse(row[rowCount++]);
                participant.setTimeStamp(fillUpTime);
                participant.setName(row[rowCount++]);
                Map<Question.QuestionType, String> predictionMap = new HashMap<>();
                predictionMap.put(Question.QuestionType.MATCH_WINNER, row[rowCount++]);
                predictionMap.put(Question.QuestionType.HIGHEST_RUN_GETTER, row[rowCount++]);
                predictionMap.put(Question.QuestionType.HIGHEST_WICKET_TAKER, row[rowCount++]);
                predictionMap.put(Question.QuestionType.MOM, row[rowCount++]);
                predictionMap.put(Question.QuestionType.RUN_RANGE, row[rowCount++]);
                predictionMap.put(Question.QuestionType.BONUS, row[rowCount]);
                participant.setPredictions(predictionMap);
                participants.add(participant);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return participants;
    }

    private static void calculatePoints(){
        for (Participant participant : participants) {
            int score = 0;
            Map<Question.QuestionType, String> prediction = participant.getPredictions();
            for(Question.QuestionType questionType : prediction.keySet()) {
                int point = 0;
                if(prediction.get(questionType).equals(Answer.get(questionType))) {
                    point = Question.getPoints(questionType);
                    score += point;
                }
                participant.setPointsForEachQuestion(questionType, point);
            }
            participant.setTotalPoints(score);
        }
    }

    private static void writeToCSV(){
        File csvFile = new File(IPLConstants.CSV_PATH + POINTS_OUTPUT_FILE_NAME);
        try(FileWriter writer = new FileWriter(csvFile);
            CSVWriter csvWriter = new CSVWriter(writer)){
            List<String[]> data = new ArrayList<>();
            data.add(POINTS_OUTPUT_CSV_COLUMNS);
            participants.forEach(participant -> data.add(participant.getCalculatedCSVRow()));
            csvWriter.writeAll(data);
        } catch (IOException e){
            System.out.println("Failed while writing to CSV!");
            e.printStackTrace();
        }
    }

    private void saveToDB() {
        for (Participant participant : participants) {
            int totalPoints = participant.getTotalPoints();
            int matchWinnerPoint = participant.getPoints().get(Question.QuestionType.MATCH_WINNER);
            int highestRunGetterPoint = participant.getPoints().get(Question.QuestionType.HIGHEST_RUN_GETTER);
            int highestWicketTakerPoint = participant.getPoints().get(Question.QuestionType.HIGHEST_WICKET_TAKER);
            int momPoint = participant.getPoints().get(Question.QuestionType.MOM);
            int runRangePoint = participant.getPoints().get(Question.QuestionType.RUN_RANGE);
            int bonusPoint = participant.getPoints().get(Question.QuestionType.BONUS);
            int strike = isAStrike(participant.getTimeStamp()) ? 1 : 0;
            Optional<Leaderboard> leaderboard = leaderboardRepository.findById(participant.getName());
            Leaderboard leaderboardRow;
            if(leaderboard.isPresent()){
                leaderboardRow = leaderboard.get();
                totalPoints += leaderboardRow.getTotalPoint();
                matchWinnerPoint += leaderboardRow.getMatchWinnerPoint();
                highestRunGetterPoint += leaderboardRow.getHighestRunGetterPoint();
                highestWicketTakerPoint += leaderboardRow.getHighestWicketTakerPoint();
                momPoint += leaderboardRow.getMomPoint();
                runRangePoint += leaderboardRow.getRunRangePoint();
                bonusPoint += leaderboardRow.getBonusPoint();
                strike += leaderboardRow.getStrike();
            } else{
                leaderboardRow = new Leaderboard();
                leaderboardRow.setName(participant.getName());
            }
            leaderboardRow.setTotalPoint(totalPoints);
            leaderboardRow.setMatchWinnerPoint(matchWinnerPoint);
            leaderboardRow.setHighestRunGetterPoint(highestRunGetterPoint);
            leaderboardRow.setHighestWicketTakerPoint(highestWicketTakerPoint);
            leaderboardRow.setMomPoint(momPoint);
            leaderboardRow.setRunRangePoint(runRangePoint);
            leaderboardRow.setBonusPoint(bonusPoint);
            leaderboardRow.setStrike(strike);
            leaderboardRepository.save(leaderboardRow);
        }
    }

    private boolean isAStrike(Date timeStamp){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timeStamp);
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 5);
        calendar.set(Calendar.SECOND, 0);
        LOGGER.info(calendar.getTime().toString());
        return calendar.getTime().before(timeStamp);
    }
}
