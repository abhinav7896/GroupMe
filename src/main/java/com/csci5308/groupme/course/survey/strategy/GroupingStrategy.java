package com.csci5308.groupme.course.survey.strategy;

import com.csci5308.groupme.course.survey.model.Candidate;
import com.csci5308.groupme.course.survey.model.Group;

import java.util.List;

public interface GroupingStrategy {

    public List<Group> getGroups(List<Candidate> candidates, Integer groupSize);

    public void setGroupingHeuristic(GroupingHeuristic groupingHeuristic);

}
