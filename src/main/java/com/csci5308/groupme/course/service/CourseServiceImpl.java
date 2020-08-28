package com.csci5308.groupme.course.service;

import com.csci5308.groupme.course.dao.CourseDao;
import com.csci5308.groupme.course.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDao courseDao;
    private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Override
    public int createCourse(Course course) throws Exception {
        int status = courseDao.save(course);
        logger.info("course saved to database :" + course.getCourseName());
        return status;
    }

    @Override
    public int delete(String courseCode) throws Exception {
        int rowCount = courseDao.remove(courseCode);
        logger.info("course removed from database :" + courseCode);
        return rowCount;
    }

}
