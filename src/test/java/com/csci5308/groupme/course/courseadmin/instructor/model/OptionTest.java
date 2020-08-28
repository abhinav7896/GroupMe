package com.csci5308.groupme.course.courseadmin.instructor.model;

import com.csci5308.groupme.course.courseadmin.instructor.model.Option;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
public class OptionTest {

    private String optionTextTest = "Beginner";
    private Integer optionIdTest = 1;
    private Integer displayOrderTest = 1;

    @Test
    public void defaultConstructorTest() {

        Option option = new Option();
        assertNull(option.getOptionText());
        assertEquals(0, option.getOptionId());
        assertEquals(0, option.getDisplayOrder());
    }

    @Test
    public void constructorWithValuesTest() {
        Option option = new Option(optionTextTest, optionIdTest, displayOrderTest);
        assertEquals(optionTextTest, option.getOptionText());
        assertEquals(optionIdTest, option.getOptionId());
        assertEquals(displayOrderTest, option.getDisplayOrder());
    }

    @Test
    public void getOptionTextTest() {
        Option option = new Option();
        option.setOptionText(optionTextTest);
        assertEquals(optionTextTest, option.getOptionText());
    }

    @Test
    public void setOptionTextTest() {
        Option option = new Option();
        option.setOptionText(optionTextTest);
        assertEquals(optionTextTest, option.getOptionText());
    }

    @Test
    public void getOptionIdTest() {
        Option option = new Option();
        option.setOptionId(optionIdTest);
        assertEquals(optionIdTest, option.getOptionId());
    }

    @Test
    public void setOptionIdTest() {
        Option option = new Option();
        option.setOptionId(optionIdTest);
        assertEquals(optionIdTest, option.getOptionId());
    }

    @Test
    public void getDisplayOrderTest() {
        Option option = new Option();
        option.setDisplayOrder(displayOrderTest);
        assertEquals(displayOrderTest, option.getDisplayOrder());

    }

    @Test
    public void setDisplayOrderTest() {
        Option option = new Option();
        option.setDisplayOrder(displayOrderTest);
        assertEquals(displayOrderTest, option.getDisplayOrder());
    }

}
