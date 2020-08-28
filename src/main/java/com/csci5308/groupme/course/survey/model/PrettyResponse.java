package com.csci5308.groupme.course.survey.model;

import com.csci5308.groupme.course.courseadmin.instructor.constants.QuestionTypeConstants;
import com.csci5308.groupme.course.survey.constants.Criteria;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class PrettyResponse {

    private String questionId;
    private String title;
    private String answer;
    private String criterion;
    private String weight;
    private String question;
    private String type;

    public PrettyResponse(Map<?, ?> questionParamsMap) {
        this.title = (String) questionParamsMap.get("title");
        if (questionParamsMap.get("type").equals(QuestionTypeConstants.MCQ_CHOOSE_MULTIPLE)) {
            List<String> multipleChoices = (List<String>) (questionParamsMap.get("answer"));
            this.answer = multipleChoices.toString();
        } else {
            this.answer = (String) questionParamsMap.get("answer");
        }
        this.criterion = (String) questionParamsMap.get("criterion");
        if (Integer.parseInt(criterion) == Criteria.SIMILARITY) {
            criterion = Criteria.DESCRIPTION_SIMILARITY;
        } else {
            criterion = Criteria.DESCRIPTION_DISSIMILARITY;
        }
        this.weight = (String) questionParamsMap.get("weight");
        this.question = (String) questionParamsMap.get("question");
        this.setType((String) questionParamsMap.get("type"));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCriterion() {
        return criterion;
    }

    public void setCriterion(String criterion) {
        this.criterion = criterion;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

}
