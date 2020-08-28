package com.csci5308.groupme.course.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.csci5308.groupme.course.dao.CourseDaoImpl;
import com.csci5308.groupme.course.model.Course;

import errors.EditCodes;

@ExtendWith(SpringExtension.class)
public class CourseServiceImplTest {

    @Mock
    private CourseDaoImpl courseDao;

    @InjectMocks
    private CourseServiceImpl courseServiceImpl;

    @Test
    public void saveCourseTest() throws Exception {
        Course course = new Course("CSCI 5308", "Adv Software Development", 12345);
        when(courseDao.save(course)).thenReturn(1);
        assertEquals(1, courseServiceImpl.createCourse(course));
    }

    @Test
    public void courseExistsTest() throws Exception {
        Course course = new Course("CSCI 5308", "Adv Software Development", 12345);
        when(courseDao.save(course)).thenReturn(EditCodes.COURSE_EXISTS);
        assertEquals(EditCodes.COURSE_EXISTS, courseServiceImpl.createCourse(course));
    }

    @Test
    public void deleteCourseTest() throws Exception {
        Course course = new Course("CSCI 5308", "Adv Software Development", 12345);
        when(courseDao.remove(course.getCourseCode())).thenReturn(1);
        assertEquals(1, courseServiceImpl.delete(course.getCourseCode()));
    }

    @Test
    public void noCourseToDeleteTest() throws Exception {
        Course course = new Course("CSCI 5308", "Adv Software Development", 12345);
        when(courseDao.remove(course.getCourseCode())).thenReturn(EditCodes.COURSE_DOES_NOT_EXIST);
        assertEquals(EditCodes.COURSE_DOES_NOT_EXIST, courseServiceImpl.delete(course.getCourseCode()));
    }


}
