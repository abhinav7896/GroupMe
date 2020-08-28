package com.csci5308.groupme.course.survey.strategy;

import com.csci5308.groupme.course.survey.constants.Heuristics;
import com.csci5308.groupme.course.survey.strategy.greedy.PairScores;

public class GroupingHeuristicFactory {

    public static GroupingHeuristic getHeuristic(String heuristic) {
        if (heuristic.equalsIgnoreCase(Heuristics.PAIR_SCORES)) {
            return new PairScores();
        }
        if (heuristic.equalsIgnoreCase(Heuristics.MOCK)) {
            return new MockHeuristic();
        }
        return null;
    }

}
