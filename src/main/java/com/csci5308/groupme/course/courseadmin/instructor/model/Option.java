package com.csci5308.groupme.course.courseadmin.instructor.model;

public class Option {

    private String optionText;
    private Integer optionId;
    private Integer displayOrder;

    public Option() {
        this.optionText = null;
        this.optionId = 0;
        this.displayOrder = 0;
    }

    public Option(String optionText, Integer optionId, Integer displayOrder) {
        this.optionText = optionText;
        this.optionId = optionId;
        this.displayOrder = displayOrder;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }
}
