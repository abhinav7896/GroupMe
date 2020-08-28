package com.csci5308.groupme.course.survey.strategy;

import ch.qos.logback.classic.Logger;
import com.csci5308.groupme.course.survey.model.Candidate;
import com.csci5308.groupme.course.survey.strategy.greedy.PairScores;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.FilePathConstants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
public class PairScoresHeuristicTest {

    private static Map<?, ?> testResponses;
    private static PairScores pairScoresHeuristic;
    private static Candidate pivotCandidate;
    private final Logger logger = (Logger) LoggerFactory.getLogger(PairScoresHeuristicTest.class);

    @BeforeAll
    public static void init() {
        pairScoresHeuristic = new PairScores();
        try {
            ObjectMapper mapper = new ObjectMapper();
            testResponses = mapper.readValue(Paths.get(FilePathConstants.TEST_RESPONSES_JSON_FILE).toFile(), Map.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        pivotCandidate = new Candidate();
    }

    @Test
    public void calculatePairScoreTest() throws Exception {
        Candidate groupMateCandidate = new Candidate();
        pivotCandidate.setQuestionResponsesMap((Map<?, ?>) testResponses.get("c1"));
        groupMateCandidate.setQuestionResponsesMap((Map<?, ?>) testResponses.get("c2"));
        Double pairScoreC1C2 = pairScoresHeuristic.calculatePairScore(pivotCandidate, groupMateCandidate);
        groupMateCandidate.setQuestionResponsesMap((Map<?, ?>) testResponses.get("c3"));
        Double pairScoreC1C3 = pairScoresHeuristic.calculatePairScore(pivotCandidate, groupMateCandidate);
        logger.debug("Pair1 score: {}", pairScoreC1C2);
        logger.debug("Pair2 score: {}", pairScoreC1C3);
        assert (pairScoreC1C2 < pairScoreC1C3);
    }

    @Test
    public void computeTest() throws Exception {
        List<Candidate> candidates = new ArrayList<Candidate>();
        pivotCandidate.setQuestionResponsesMap((Map<?, ?>) testResponses.get("c1"));
        for (Map.Entry<?, ?> entry : testResponses.entrySet()) {
            Candidate candidate = new Candidate();
            candidate.setQuestionResponsesMap((Map<?, ?>) entry.getValue());
            candidates.add(candidate);
        }
        List<Candidate> groupMateCandidates = pairScoresHeuristic.compute(candidates, pivotCandidate);
        for (Candidate groupMateCandidate : groupMateCandidates) {
            logger.info("Fitness: {} ", groupMateCandidate.getFitness());
        }
        assertNotNull(groupMateCandidates);
        assert (groupMateCandidates.size() > 0);
    }
}
