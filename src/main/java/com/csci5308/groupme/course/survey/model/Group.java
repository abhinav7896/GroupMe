package com.csci5308.groupme.course.survey.model;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private final Logger logger = (Logger) LoggerFactory.getLogger(Group.class);

    private Integer groupNo;
    private List<Candidate> candidates;
    private Candidate pivotCandidate;

    public Group() {
        candidates = new ArrayList<Candidate>();
    }

    public Integer getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(Integer groupNo) {
        this.groupNo = groupNo;
        System.out.println("group no = " + this.groupNo);
    }

    public Candidate getPivotCandidate() {
        return pivotCandidate;
    }

    public void setPivotCandidate(Candidate pivotCandidate) {
        this.pivotCandidate = pivotCandidate;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> group) {
        this.candidates = group;
    }

    public void add(Candidate candidate) {
        candidates.add(candidate);
        logger.debug("Adding single candidate", candidates.size());
    }

    public void sortCandidatesByFitness() {
        candidates.sort((candidate1, candidate2) -> Double.compare(candidate2.getFitness(), candidate1.getFitness()));
    }

}
