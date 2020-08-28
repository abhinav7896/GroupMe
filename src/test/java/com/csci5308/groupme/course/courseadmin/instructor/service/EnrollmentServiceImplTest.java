package com.csci5308.groupme.course.courseadmin.instructor.service;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.TestsConfig;
import com.csci5308.groupme.course.courseadmin.instructor.service.EnrollmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
public class EnrollmentServiceImplTest {

    private Reader reader;

    @Test
    public void readAllTest() {
        EnrollmentService enrollmentService = TestsConfig.instance().getEnrollmentService();
        try {
            reader = new BufferedReader(new FileReader("src/test/resources/validrecords.csv"));
            List<String[]> records = enrollmentService.readAll(reader);
            for (String[] record : records) {
                assertEquals(4, record.length);
            }

            reader = new BufferedReader(new FileReader("src/test/resources/invalidrecords.csv"));
            List<String[]> records_invalid = enrollmentService.readAll(reader);
            for (String[] record : records_invalid) {
                assertNotEquals(4, record.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
