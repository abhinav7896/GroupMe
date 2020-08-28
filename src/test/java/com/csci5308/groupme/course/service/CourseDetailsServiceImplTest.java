package com.csci5308.groupme.course.service;

import com.csci5308.groupme.course.dao.CourseDetailsDaoImpl;
import com.csci5308.groupme.course.model.Course;
import constants.Roles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CourseDetailsServiceImplTest {

    @Mock
    private CourseDetailsDaoImpl courseDao;

    @InjectMocks
    private CourseDetailsServiceImpl courseDetailsServiceImpl;

    private Logger logger = LoggerFactory.getLogger(CourseDetailsServiceImplTest.class);

    @Test
    public void findAllCoursesTest() throws Exception {

        List<Course> defaultCourseList = new ArrayList<>();
        Course courseOne = new Course("CSCI5308", "Adv Software Development", 12345);
        defaultCourseList.add(courseOne);
        when(courseDao.findAllCourses()).thenReturn(defaultCourseList);
        List<Course> checkCourseList = courseDetailsServiceImpl.findAllCourses();
        logger.info("Found Course: {}", checkCourseList);
        assertEquals(1, checkCourseList.size());
    }

    @Test
    public void getCoursesByUserNameAndRoleTest() throws Exception {

        String userName = "ysavani";
        String roleName = Roles.TA;
        List<Course> defaultCourseList = new ArrayList<>();
        Course courseOne = new Course("CSCI5308", "Adv Software Development", 12345);
        defaultCourseList.add(courseOne);
        when(courseDao.getCoursesByUserNameAndRole(userName, roleName)).thenReturn(defaultCourseList);
        List<Course> checkCourseList = courseDetailsServiceImpl.getCoursesByUserNameAndRole(userName, roleName);
        logger.info("Found Course: {} for username : {}", checkCourseList, userName);
        assertEquals(1, checkCourseList.size());
    }

    @Test
    public void findCoursesByStudentUserNameTest() throws Exception {

        String studentUserName = "ysavani";
        List<Course> defaultCourseList = new ArrayList<>();
        Course courseOne = new Course("CSCI 5308", "Adv Software Development", 12345);
        defaultCourseList.add(courseOne);
        when(courseDao.findCoursesByStudentUserName(studentUserName)).thenReturn(defaultCourseList);
        List<Course> checkCourseList = courseDetailsServiceImpl.findCoursesByStudentUserName(studentUserName);
        logger.info("Found Course: {} student : {} enrolled in", checkCourseList, studentUserName);
        assertEquals(1, checkCourseList.size());
    }

}
