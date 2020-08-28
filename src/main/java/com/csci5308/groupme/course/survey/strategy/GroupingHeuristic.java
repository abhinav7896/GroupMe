package com.csci5308.groupme.course.survey.strategy;

import com.csci5308.groupme.course.survey.model.Candidate;

import java.util.List;

public interface GroupingHeuristic {

    public Object compute(List<Candidate> candidates, Candidate... predicates);

}
