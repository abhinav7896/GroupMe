package com.csci5308.groupme.course.survey.strategy.greedy;

import ch.qos.logback.classic.Logger;
import com.csci5308.groupme.course.courseadmin.instructor.constants.QuestionTypeConstants;
import com.csci5308.groupme.course.survey.constants.Criteria;
import com.csci5308.groupme.course.survey.model.Candidate;
import com.csci5308.groupme.course.survey.strategy.GroupingHeuristic;
import org.slf4j.LoggerFactory;

import java.util.*;

@SuppressWarnings("unchecked")
public class PairScores implements GroupingHeuristic {

    private final Logger logger = (Logger) LoggerFactory.getLogger(PairScores.class);

    private final int SIMILARITY = 1;
    private final int DISSIMILARITY = 2;
    private final double resolver = 1.25;
    private Map<?, ?> pivotResponses;
    private Map<?, ?> groupMateCandidateResponses;

    @Override
    public List<Candidate> compute(List<Candidate> candidates, Candidate... predicates) {
        if (candidates != null) {
            double pairScore;
            Candidate pivotCandidate = predicates[0];
            for (Candidate candidate : candidates) {
                candidate.setFitness(0.0);
            }
            for (Candidate candidate : candidates) {
                pairScore = calculatePairScore(pivotCandidate, candidate);
                candidate.setFitness(pairScore);
            }
        }
        return candidates;
    }

    public double calculatePairScore(Candidate pivotCandidate, Candidate groupMateCandidate) {
        double score = 0.0;
        this.pivotResponses = pivotCandidate.getQuestionResponsesMap();
        this.groupMateCandidateResponses = groupMateCandidate.getQuestionResponsesMap();
        List<String> questionIds = new ArrayList<String>();
        for (Map.Entry<?, ?> entry : pivotResponses.entrySet()) {
            questionIds.add((String) entry.getKey());
        }
        for (String questionId : questionIds) {
            score += weight(questionId) * gain(questionId);
        }
        logger.debug("Pair Score {}", score);
        return score;
    }

    private double gain(String questionId) {
        double gain = 0.00;
        int criterion = Integer.parseInt((String) ((Map<?, ?>) pivotResponses.get(questionId)).get("criterion"));
        String questionType = (String) ((Map<?, ?>) pivotResponses.get(questionId)).get("type");
        int expectedCriterion = 0;
        if (criterion == Criteria.DISSIMILARITY) {
            expectedCriterion = DISSIMILARITY;
        } else {
            expectedCriterion = SIMILARITY;
        }
        double pivotAnswer;
        double candidateAnswer;
        switch (questionType) {

            case QuestionTypeConstants.NUMERIC:
                logger.debug("Numeric type question, {}", questionId);
                pivotAnswer = Double.parseDouble((String) ((Map<?, ?>) pivotResponses.get(questionId)).get("answer"));
                candidateAnswer = Double
                        .parseDouble((String) ((Map<?, ?>) groupMateCandidateResponses.get(questionId)).get("answer"));
                if (expectedCriterion == SIMILARITY && (pivotAnswer - candidateAnswer) == 0) {
                    gain = 1;
                } else {
                    gain = Math.pow(resolver * Math.abs(pivotAnswer - candidateAnswer), Math.pow(-1, expectedCriterion));
                }
                break;

            case QuestionTypeConstants.MCQ_CHOOSE_ONE:
                logger.debug("MCQ- Choose one type question, {}", questionId);
                pivotAnswer = Double.parseDouble((String) ((Map<?, ?>) pivotResponses.get(questionId)).get("answer"));
                candidateAnswer = Double
                        .parseDouble((String) ((Map<?, ?>) groupMateCandidateResponses.get(questionId)).get("answer"));

                int observed;
                if ((pivotAnswer - candidateAnswer) == 0) {
                    observed = SIMILARITY;
                } else {
                    observed = DISSIMILARITY;
                }
                gain = (1 - Math.abs(expectedCriterion - observed));
                break;

            case QuestionTypeConstants.MCQ_CHOOSE_MULTIPLE:
                logger.debug("MCQ- Choose many type question, {}", questionId);
                List<String> pivotChoicesList = (List<String>) ((Map<?, ?>) pivotResponses.get(questionId)).get("answer");
                List<String> candidateChoicesList = (List<String>) ((Map<?, ?>) groupMateCandidateResponses.get(questionId))
                        .get("answer");
                Set<String> union = new HashSet<>(pivotChoicesList);
                union.addAll(candidateChoicesList);
                Set<String> intersection = new HashSet<>(pivotChoicesList);
                intersection.retainAll(candidateChoicesList);
                if (expectedCriterion == SIMILARITY) {
                    gain = intersection.size();
                } else {
                    union.removeAll(intersection);
                    gain = union.size();
                }
                break;

            case QuestionTypeConstants.FREE_TEXT:
                logger.debug("Free-text type question, {}", questionId);
                String pivotText = (String) ((Map<?, ?>) pivotResponses.get(questionId)).get("answer");
                String candidateText = (String) ((Map<?, ?>) groupMateCandidateResponses.get(questionId)).get("answer");
                boolean isAMatch = pivotText.equalsIgnoreCase(candidateText);
                if (expectedCriterion == SIMILARITY) {
                    if (isAMatch == true) {
                        gain = 1.0;
                    } else {
                        gain = 0.0;
                    }
                }
                if (expectedCriterion == DISSIMILARITY) {
                    if (isAMatch == false) {
                        gain = 1.0;
                    } else {
                        gain = 0.0;
                    }
                }
                break;
            default:
                gain = 0.00;
        }
        return gain;
    }

    private int weight(String questionId) {
        String weight = (String) ((Map<?, ?>) pivotResponses.get(questionId)).get("weight");
        return Integer.parseInt(weight);
    }

}
