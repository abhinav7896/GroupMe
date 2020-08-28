package com.csci5308.groupme.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.csci5308.groupme.admin.dao.AdminDao;
import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseDetailsService;
import com.csci5308.groupme.course.courseadmin.instructor.model.Instructor;
import com.csci5308.groupme.course.courseadmin.instructor.service.InstructorService;
import com.csci5308.groupme.user.model.User;
import com.csci5308.groupme.user.service.UserService;

import errors.EditCodes;

@ExtendWith(SpringExtension.class)
public class AdminServiceTest {

    @Mock
    InstructorService instructorService;
    @Mock
    CourseDetailsService courseDetailsService;
    @Mock
    UserService userService;
    @Mock
    private AdminDao adminDao;
    @InjectMocks
    private AdminServiceImpl adminServiceImpl;

    @Test
    public void assignInstructorToCourseTest() throws Exception {
        String email = "instructor_test@gmail.com";
        String courseCode = "CSCI0000";
        Instructor instructor = new Instructor();
        instructor.setUserName("testinst");
        instructor.setFirstName("Test");
        instructor.setLastName("Instructor");
        instructor.setEmail(email);
        Course course = new Course();
        course.setCourseCode(courseCode);
        course.setCourseName("Test Course");
        when(instructorService.getByEmail(email)).thenReturn(instructor);
        when(courseDetailsService.getByCourseCode(courseCode)).thenReturn(course);
        when(adminDao.createClass(instructor.getUserName(), courseCode)).thenReturn(1);
        int status = (adminServiceImpl.assignInstructorToCourse(email, courseCode));
        assertEquals(EditCodes.SUCCESS, status);
    }

    @Test
    public void createAndAssignInstructorToCourseTest() throws Exception {
        String email = "test_user@gmail.com";
        String courseCode = "CSCI0000";
        Instructor instructor = new Instructor();
        User user = new User();
        user.setUserName("testuser123");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail(email);
        Course course = new Course();
        course.setCourseCode(courseCode);
        course.setCourseName("Test Course");
        instructor.setUserName(user.getUserName());
        when(instructorService.getByEmail(email)).thenReturn(null);
        when(courseDetailsService.getByCourseCode(courseCode)).thenReturn(course);
        when(userService.getByEmail(email)).thenReturn(user);
        when(instructorService.createInstructor(instructor)).thenReturn(1);
        when(adminDao.createClass(user.getUserName(), courseCode)).thenReturn(1);
        when(instructorService.getByEmail(email)).thenReturn(instructor);
        int status = (adminServiceImpl.assignInstructorToCourse(email, courseCode));
        assertEquals(EditCodes.SUCCESS, status);
    }

    @Test
    public void instructorNotFoundTest() throws Exception {
        String email = "instructor_test@gmail.com";
        String courseCode = "CSCI0000";
        Instructor instructor = new Instructor();
        instructor.setUserName("testinst");
        instructor.setFirstName("Test");
        instructor.setLastName("Instructor");
        instructor.setEmail(email);
        Course course = new Course();
        course.setCourseCode(courseCode);
        course.setCourseName("Test Course");
        when(instructorService.getByEmail(email)).thenReturn(null);
        when(userService.getByEmail(email)).thenReturn(null);
        when(courseDetailsService.getByCourseCode(courseCode)).thenReturn(course);
        when(adminDao.createClass(instructor.getUserName(), courseCode)).thenReturn(1);
        int status = (adminServiceImpl.assignInstructorToCourse(email, courseCode));
        assertEquals(EditCodes.EMAIL_DOES_NOT_EXIST, status);
    }

    @Test
    public void classAlreadyExistsTest() throws Exception {
        String email = "instructor_test@gmail.com";
        String courseCode = "CSCI0000";
        Instructor instructor = new Instructor();
        instructor.setUserName("testinst");
        instructor.setFirstName("Test");
        instructor.setLastName("Instructor");
        instructor.setEmail(email);
        Course course = new Course();
        course.setCourseCode(courseCode);
        course.setCourseName("Test Course");
        when(instructorService.getByEmail(email)).thenReturn(instructor);
        when(courseDetailsService.getByCourseCode(courseCode)).thenReturn(course);
        when(adminDao.createClass(instructor.getUserName(), courseCode)).thenReturn(EditCodes.CLASS_ALREADY_CREATED);
        int status = (adminServiceImpl.assignInstructorToCourse(email, courseCode));
        assertEquals(EditCodes.CLASS_ALREADY_CREATED, status);
    }

    @Test
    public void courseNotFoundTest() throws Exception {
        String email = "instructor_test@gmail.com";
        String courseCode = "CSCI0000";
        Instructor instructor = new Instructor();
        instructor.setUserName("testinst");
        instructor.setFirstName("Test");
        instructor.setLastName("Instructor");
        instructor.setEmail(email);
        Course course = new Course();
        course.setCourseCode(courseCode);
        course.setCourseName("Test Course");
        when(instructorService.getByEmail(email)).thenReturn(instructor);
        when(courseDetailsService.getByCourseCode(courseCode)).thenReturn(null);
        int status = (adminServiceImpl.assignInstructorToCourse(email, courseCode));
        assertEquals(EditCodes.COURSE_DOES_NOT_EXIST, status);
    }

    @Test
    public void dbIssueTest() throws Exception {
        String email = "instructor_test@gmail.com";
        String courseCode = "CSCI0000";
        Instructor instructor = new Instructor();
        instructor.setUserName("testinst");
        instructor.setFirstName("Test");
        instructor.setLastName("Instructor");
        instructor.setEmail(email);
        Course course = new Course();
        course.setCourseCode(courseCode);
        course.setCourseName("Test Course");
        when(instructorService.getByEmail(email)).thenReturn(instructor);
        when(courseDetailsService.getByCourseCode(courseCode)).thenReturn(course);
        when(adminDao.createClass(instructor.getUserName(), courseCode)).thenReturn(0);
        int status = (adminServiceImpl.assignInstructorToCourse(email, courseCode));
        assertEquals(EditCodes.FAILURE, status);
    }
}
