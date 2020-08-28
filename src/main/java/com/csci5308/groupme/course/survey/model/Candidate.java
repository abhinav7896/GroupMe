package com.csci5308.groupme.course.survey.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Candidate {

    private final Logger logger = LoggerFactory.getLogger(Candidate.class);
    private Integer surveyId;
    private String userName;
    private String bannerId;
    private String firstName;
    private String lastName;
    private String stringifiedResponses;
    private Double fitness;
    private Map<?, ?> questionResponsesMap;
    private List<PrettyResponse> prettyResponses;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getStringifiedResponses() {
        return stringifiedResponses;
    }

    public void setStringifiedResponses(String responses) {
        this.stringifiedResponses = responses;
    }

    public Double getFitness() {
        return fitness;
    }

    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }

    public Map<?, ?> getQuestionResponsesMap() {
        return questionResponsesMap;
    }

    public void setQuestionResponsesMap(Map<?, ?> questionResponsesMap) {
        this.questionResponsesMap = questionResponsesMap;
    }

    public List<PrettyResponse> getPrettyResponses() {
        prettyResponses = new ArrayList<PrettyResponse>();
        for (Map.Entry<?, ?> entry : questionResponsesMap.entrySet()) {
            String questionId = (String) entry.getKey();
            Map<?, ?> questionParamsMap = (Map<?, ?>) questionResponsesMap.get(questionId);
            PrettyResponse prettyResponse = new PrettyResponse(questionParamsMap);
            prettyResponse.setQuestionId(questionId);
            this.prettyResponses.add(prettyResponse);
        }
        return prettyResponses;
    }

    public void storeResponsesAsMap(String stringifiedResponses) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.questionResponsesMap = mapper.readValue(stringifiedResponses, Map.class);
        } catch (JsonProcessingException e) {
            logger.error("Failed to convert from JSON string to Map");
            e.printStackTrace();
        }

    }

}
