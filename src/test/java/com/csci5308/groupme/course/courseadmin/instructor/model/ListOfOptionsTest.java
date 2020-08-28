package com.csci5308.groupme.course.courseadmin.instructor.model;

import com.csci5308.groupme.course.courseadmin.instructor.model.ListOfOptions;
import com.csci5308.groupme.course.courseadmin.instructor.model.Option;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class ListOfOptionsTest {

    @Test
    public void getOptionListTest() {
        List<Option> optionListTest = new ArrayList<>();
        optionListTest.add(new Option("Beginner", 1, 1));
        ListOfOptions options = new ListOfOptions();
        options.setOptionList(optionListTest);
        assertEquals(optionListTest.size(), options.getOptionList().size());
    }

    @Test
    public void setOptionListTest() {
        List<Option> optionListTest = new ArrayList<>();
        optionListTest.add(new Option("Beginner", 1, 1));
        ListOfOptions options = new ListOfOptions();
        options.setOptionList(optionListTest);
        assertEquals(optionListTest.size(), options.getOptionList().size());
    }

}
