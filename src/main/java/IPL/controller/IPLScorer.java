package IPL.controller;

import IPL.ParticipantService;
import IPL.constant.IPLConstants;
import IPL.form.AnswerForm;
import IPL.model.Leaderboard;
import IPL.model.LeaderboardRepository;
import IPL.model.Participant;
import IPL.model.Question;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private static final int COLUMNS = 31;
    private static final String INPUT_PREDICTION_FILE_NAME = "Input/Day26.csv";
    private static final String POINTS_OUTPUT_FILE_NAME = "Output/Day.csv";
    private static final String[] POINTS_OUTPUT_CSV_COLUMNS = new String[]{
            IPLConstants.NAME,
            IPLConstants.TOTAL_POINTS,
            IPLConstants.MATCH_WINNER,
            IPLConstants.RUNS,
            IPLConstants.WICKETS,
            IPLConstants.MOM,
            IPLConstants.RUN_RANGE,
            IPLConstants.BONUS1,
            IPLConstants.BONUS2,
            IPLConstants.BONUS3,
            IPLConstants.BONUS4,
            IPLConstants.BONUS5,
            IPLConstants.BONUS6,
            IPLConstants.BONUS7,
            IPLConstants.BONUS8,
            IPLConstants.BONUS9,
            IPLConstants.BONUS10,
            IPLConstants.BONUS11,
            IPLConstants.BONUS12,
            IPLConstants.BONUS13,
            IPLConstants.BONUS14,
            IPLConstants.BONUS15,
            IPLConstants.BONUS16,
            IPLConstants.BONUS17,
            IPLConstants.BONUS18,
            IPLConstants.BONUS19,
            IPLConstants.BONUS20,
            IPLConstants.BONUS21,
            IPLConstants.BONUS22,
            IPLConstants.BONUS23,
            IPLConstants.BONUS24
    };

    @ResponseBody
    @RequestMapping(value = "/calculateScore", method = RequestMethod.POST)
    public String calculateScore(@RequestBody AnswerForm answers) {
        LOGGER.info("Calculation started.");
        Question.setAnswers(answers);
        participants = getParticipants();
        LOGGER.info("Participants count = " + participants.size());
        calculatePoints();
        writeToCSV();
        //saveToDB();
        LOGGER.info("Calculation done.");
        return "All Good.";
    }

    private List<Participant> getParticipants(){
        List<Participant> participants = new ArrayList<>();
        String line;
        boolean isColumnRow = true;
        try(BufferedReader br = new BufferedReader(new FileReader(IPLConstants.CSV_PATH + INPUT_PREDICTION_FILE_NAME))) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(IPLConstants.SPREAD_SHEET_TIMESTAMP_PATTERN);
            while ((line = br.readLine()) != null) {
                if(isColumnRow) {
                    String[] columns = line.split(IPLConstants.COMMA_DELIMITER);
                    if(columns.length != COLUMNS){
                        throw new RuntimeException("Incorrect no of columns - " + columns.length);
                    }
                    isColumnRow = false;
                    continue;
                }
                String[] row = line.split(IPLConstants.COMMA_DELIMITER);
                int rowCount = 0;
                Participant participant = new Participant();
                Date fillUpTime = simpleDateFormat.parse(row[rowCount++]);
                participant.setTimeStamp(fillUpTime);
                participant.setEmail(row[rowCount++]);
                Map<Question.QuestionType, String> predictionMap = new HashMap<>();
                predictionMap.put(Question.StaticQuestions.MATCH_WINNER, row[rowCount++]);
                predictionMap.put(Question.StaticQuestions.HIGHEST_RUN_GETTER, row[rowCount++]);
                predictionMap.put(Question.StaticQuestions.HIGHEST_WICKET_TAKER, row[rowCount++]);
                predictionMap.put(Question.StaticQuestions.MOM, row[rowCount++]);
                predictionMap.put(Question.StaticQuestions.RUN_RANGE, row[rowCount++]);
                for(int i = 1; rowCount < row.length; i++) {
                    predictionMap.put(Question.BonusQuestions.valueOf(IPLConstants.BONUS_PREFIX + i), row[rowCount++]);
                }
                participant.setPredictions(predictionMap);
                LOGGER.info("Map >>>> " + predictionMap);
                Optional<Leaderboard> predictor = leaderboardRepository.findById(participant.getEmail());
                if(predictor.isPresent()){
                    Leaderboard actualPredictor = predictor.get();
                    participant.setName(actualPredictor.getName());
                } else{
                    participant.setName(participant.getEmail());
                }
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
                List<String> answers = Arrays.asList(Question.getAnswer(questionType).getAnswerValue());
                if(answers.contains(prediction.get(questionType))) {
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
            participants.forEach(participant -> data.add(ParticipantService.getCalculatedCSVRow(participant)));
            csvWriter.writeAll(data);
        } catch (IOException e){
            System.out.println("Failed while writing to CSV!");
            e.printStackTrace();
        }
    }

    private void saveToDB() {
        for (Participant participant : participants) {
            int totalPoints = participant.getTotalPoints();
            int matchWinnerPoint = participant.getPoints().get(Question.StaticQuestions.MATCH_WINNER);
            int highestRunGetterPoint = participant.getPoints().get(Question.StaticQuestions.HIGHEST_RUN_GETTER);
            int highestWicketTakerPoint = participant.getPoints().get(Question.StaticQuestions.HIGHEST_WICKET_TAKER);
            int momPoint = participant.getPoints().get(Question.StaticQuestions.MOM);
            int runRangePoint = participant.getPoints().get(Question.StaticQuestions.RUN_RANGE);
            int bonusPoint = ParticipantService.calculateBonusPoints(participant.getPoints());
            Optional<Leaderboard> predictor = leaderboardRepository.findById(participant.getEmail());
            Leaderboard actualPredictor;
            if(predictor.isPresent()){
                actualPredictor = predictor.get();
                totalPoints += actualPredictor.getTotalPoint();
                matchWinnerPoint += actualPredictor.getMatchWinnerPoint();
                highestRunGetterPoint += actualPredictor.getHighestRunGetterPoint();
                highestWicketTakerPoint += actualPredictor.getHighestWicketTakerPoint();
                momPoint += actualPredictor.getMomPoint();
                runRangePoint += actualPredictor.getRunRangePoint();
                bonusPoint += actualPredictor.getBonusPoint();
            } else{
                actualPredictor = new Leaderboard();
                actualPredictor.setEmail(participant.getEmail());
                actualPredictor.setName(participant.getName());
            }
            actualPredictor.setTotalPoint(totalPoints);
            actualPredictor.setMatchWinnerPoint(matchWinnerPoint);
            actualPredictor.setHighestRunGetterPoint(highestRunGetterPoint);
            actualPredictor.setHighestWicketTakerPoint(highestWicketTakerPoint);
            actualPredictor.setMomPoint(momPoint);
            actualPredictor.setRunRangePoint(runRangePoint);
            actualPredictor.setBonusPoint(bonusPoint);
            leaderboardRepository.save(actualPredictor);
        }
    }
}
