package com.csci5308.groupme.course.survey.strategy.greedy;

import ch.qos.logback.classic.Logger;
import com.csci5308.groupme.course.survey.model.Candidate;
import com.csci5308.groupme.course.survey.model.Group;
import com.csci5308.groupme.course.survey.strategy.GroupingHeuristic;
import com.csci5308.groupme.course.survey.strategy.GroupingStrategy;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class GreedyGroupingWithPairScores implements GroupingStrategy {

    private final Logger logger = (Logger) LoggerFactory.getLogger(GreedyGroupingWithPairScores.class);
    private GroupingHeuristic groupingHeuristic;
    private List<Group> groups;
    private List<Candidate> pivotCandidates;

    @Override
    public void setGroupingHeuristic(GroupingHeuristic groupingHeuristic) {
        this.groupingHeuristic = groupingHeuristic;
        groups = new ArrayList<Group>();
    }

    @Override
    public List<Group> getGroups(List<Candidate> candidates, Integer groupSize) {
        pivotCandidates = selectPivotCandidates(candidates);
        if (pivotCandidates.isEmpty()) {
            groups = getGroupsUsingArbitraryPivots(candidates, groupSize);
        } else {
            groups = getGroupsUsingObtainedPivots(candidates, groupSize);
        }
        return groups;
    }

    public List<Group> getGroupsUsingArbitraryPivots(List<Candidate> allCandidates, Integer groupSize) {
        logger.debug("Forming groups with arbitrary pivots...");
        int totalNumberOfCandidates = allCandidates.size();
        List<Candidate> pivotCandidates = new ArrayList<Candidate>();
        int groupNo = 1;
        for (int i = 0; i < totalNumberOfCandidates / groupSize; i++) {
            pivotCandidates.add(allCandidates.remove(i));
        }
        for (Candidate pivotCandidate : pivotCandidates) {
            Group group = generateOneGreedyGroup(allCandidates, pivotCandidate, groupSize);
            group.setGroupNo(groupNo++);
            group.setPivotCandidate(pivotCandidate);
            groups.add(group);
            allCandidates.removeAll(group.getCandidates());
        }
        if (allCandidates.isEmpty() == false) {
            logger.debug("{} candidates remaining. Forming the remainder candidates group..", allCandidates.size());
            Group group = new Group();
            group.setGroupNo(groupNo++);
            group.setCandidates(allCandidates);
            groups.add(group);
            logger.debug("Last group size: {}", group.getCandidates().size());
        }
        logger.debug("Total number of groups formed: {}", groups.size());
        return groups;
    }

    public List<Group> getGroupsUsingObtainedPivots(List<Candidate> allCandidates, Integer groupSize) {
        logger.debug("Forming groups with arbitrary pivots...");
        return null;
    }

    public Group generateOneGreedyGroup(List<Candidate> allCandidates, Candidate pivotCandidate, Integer groupSize) {
        List<Candidate> groupMateCandidates = (List<Candidate>) groupingHeuristic.compute(allCandidates,
                pivotCandidate);
        groupMateCandidates
                .sort((candidate1, candidate2) -> Double.compare(candidate2.getFitness(), candidate1.getFitness()));
        Group group = new Group();
        logger.debug("Adding pivot candidate {}", pivotCandidate);
        for (int i = 0; i < groupSize - 1; i++) {
            logger.debug("Adding candidate-{} with fitness: {}", i + 2, groupMateCandidates.get(i).getFitness());
            group.add(groupMateCandidates.get(i));
        }
        group.sortCandidatesByFitness();
        group.add(pivotCandidate);
        return group;
    }

    private List<Candidate> selectPivotCandidates(List<Candidate> candidates) {
        List<Candidate> pivots = new ArrayList<Candidate>();
        for (Candidate candidate : candidates) {
            if (meetsBoundedCriteria(candidate)) {
                pivots.add(candidate);
            }
        }
        return pivots;
    }

    private boolean meetsBoundedCriteria(Candidate candidate) {
        return false;
    }
}
