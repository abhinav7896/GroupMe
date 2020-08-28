package com.csci5308.groupme.course.service;

import com.csci5308.groupme.course.dao.CourseDetailsDao;
import com.csci5308.groupme.course.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseDetailsServiceImpl implements CourseDetailsService {

    @Autowired
    CourseDetailsDao courseDetailsDao;
    private Logger logger = LoggerFactory.getLogger(CourseDetailsServiceImpl.class);

    @Override
    public List<Course> findAllCourses() throws Exception {
        logger.info("Find all courses");
        return courseDetailsDao.findAllCourses();
    }

    @Override
    public List<Course> getCoursesByUserNameAndRole(String userName, String roleName) throws Exception {
        logger.info("Find course by username and its role");
        return courseDetailsDao.getCoursesByUserNameAndRole(userName, roleName);
    }

    @Override
    public Course getByCourseCode(String courseCode) throws Exception {
        Course course = courseDetailsDao.findCourseByCourseCode(courseCode);
        logger.info("Found course by CourseCode: " + courseCode);
        return course;
    }

    @Override
    public List<Course> findCoursesByStudentUserName(String studentUserName) throws Exception {
        logger.info("Find course by student's username");
        return courseDetailsDao.findCoursesByStudentUserName(studentUserName);
    }

    @Override
    public List<Course> findCoursesByInstructor(String instructorUserName) throws Exception {
        List<Course> coursesList = courseDetailsDao.findCoursesByInstructor(instructorUserName);
        logger.info("Instructor: " + instructorUserName + " and its courses: " + coursesList);
        return coursesList;
    }

}
