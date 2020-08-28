package com.csci5308.groupme.course.courseadmin.instructor.service;

import com.csci5308.groupme.course.courseadmin.instructor.dao.InstructorDaoImpl;
import com.csci5308.groupme.course.courseadmin.instructor.model.Instructor;
import com.csci5308.groupme.user.model.User;
import com.csci5308.groupme.user.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class InstructorServiceImplTest {

    @Mock
    UserServiceImpl userService;

    @Mock
    InstructorDaoImpl instructorDao;

    @InjectMocks
    InstructorServiceImpl instructorService;

    private Logger logger = LoggerFactory.getLogger(InstructorServiceImplTest.class);

    @Test
    public void createInstructorTest() throws Exception {
        Instructor instructor = new Instructor();
        instructor.setUserName("inst_test");
        instructor.setAbout("PhD");
        when(instructorDao.save(instructor)).thenReturn(1);
        int status = instructorService.createInstructor(instructor);
        logger.info("Instructor Created with username : {}", instructor.getUserName());
        assertEquals(1, status);
    }

    @Test
    public void getByUserNameTest() throws Exception {
        String userName = "inst_test";
        Instructor instructorTest = new Instructor();
        instructorTest.setFirstName("karan");
        instructorTest.setUserName("inst_test");
        instructorTest.setAbout("PhD");
        User user = new User();
        user.setFirstName("karan");
        user.setUserName("inst_test");
        when(instructorDao.findByUserName(userName)).thenReturn(instructorTest);
        when(userService.getByUserName(userName)).thenReturn(user);
        Instructor instructor = instructorService.getByUserName(userName);
        logger.info("Found Instructor firstname: {}, lastname: {} by username: {}",
                instructor.getFirstName(), instructorTest.getFirstName(), userName);
        assertEquals(instructor.getFirstName(), instructorTest.getFirstName());
    }

    @Test
    public void getByEmailTest() throws Exception {
        String email = "instructor@dal.ca";
        User user = new User();
        user.setFirstName("karan");
        user.setUserName("inst_test");
        Instructor instructorTest = new Instructor();
        instructorTest.setFirstName("karan");
        instructorTest.setUserName("inst_test");
        instructorTest.setAbout("PhD");
        when(userService.getByEmail(email)).thenReturn(user);
        when(instructorDao.findByUserName(user.getUserName())).thenReturn(instructorTest);
        Instructor instructor = instructorService.getByEmail(email);
        logger.info("Found Instructor firstname: {}, lastname: {} by email: {}",
                instructor.getFirstName(), instructorTest.getFirstName(), email);
        assertEquals(instructor.getFirstName(), instructorTest.getFirstName());
    }
}
