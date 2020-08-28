package com.csci5308.groupme.admin.service;

import ch.qos.logback.classic.Logger;
import com.csci5308.groupme.admin.dao.AdminDao;
import com.csci5308.groupme.course.courseadmin.instructor.model.Instructor;
import com.csci5308.groupme.course.courseadmin.instructor.service.InstructorService;
import com.csci5308.groupme.course.courseadmin.instructor.service.InstructorServiceImpl;
import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseDetailsService;
import com.csci5308.groupme.user.model.User;
import com.csci5308.groupme.user.service.UserService;
import constants.Roles;
import errors.EditCodes;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final Logger logger = (Logger) LoggerFactory.getLogger(InstructorServiceImpl.class);

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private CourseDetailsService courseDetailsService;

    @Autowired
    private UserService userService;

    @Override
    public int assignInstructorToCourse(String emailId, String courseCode) throws Exception {
        int status = 0;
        Instructor instructor = instructorService.getByEmail(emailId);
        Course course = courseDetailsService.getByCourseCode(courseCode);
        if (null == course) {
            status = EditCodes.COURSE_DOES_NOT_EXIST;
        } else if (null == instructor) {
            status = makeUserAsInstructor(emailId);
            if (status == EditCodes.EMAIL_DOES_NOT_EXIST) {
                status = EditCodes.EMAIL_DOES_NOT_EXIST;
            } else if (status == EditCodes.SUCCESS) {
                status = this.assignInstructorToCourse(emailId, courseCode);
            } else {
                status = EditCodes.INSTRUCTOR_NOT_CREATED;
            }
        } else {
            status = adminDao.createClass(instructor.getUserName(), course.getCourseCode());
            logger.info("Status {}", status);
            if (status == EditCodes.CLASS_ALREADY_CREATED) {
                status = EditCodes.CLASS_ALREADY_CREATED;
            } else if (status == EditCodes.SUCCESS) {
                status = EditCodes.SUCCESS;
            } else {
                status = EditCodes.FAILURE;
            }
        }
        return status;
    }

    @Override
    public int makeUserAsInstructor(String emailId) throws Exception {
        User user = userService.getByEmail(emailId);
        Instructor instructor = new Instructor();
        int status = 0;
        if (null == user) {
            status = EditCodes.EMAIL_DOES_NOT_EXIST;
        } else {
            logger.info(user.getUserName());
            status = userService.addRole(user.getUserName(), Roles.INSTRUCTOR);
            if (status == EditCodes.SUCCESS) {
                instructor.setUserName(user.getUserName());
                status = instructorService.createInstructor(instructor);
            }
        }
        return status;
    }
}
