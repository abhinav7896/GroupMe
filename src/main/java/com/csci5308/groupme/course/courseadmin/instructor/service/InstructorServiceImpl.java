package com.csci5308.groupme.course.courseadmin.instructor.service;

import ch.qos.logback.classic.Logger;
import com.csci5308.groupme.course.courseadmin.instructor.dao.InstructorDao;
import com.csci5308.groupme.course.courseadmin.instructor.model.Instructor;
import com.csci5308.groupme.user.model.User;
import com.csci5308.groupme.user.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final Logger logger = (Logger) LoggerFactory.getLogger(InstructorServiceImpl.class);

    @Autowired
    private InstructorDao instructorDao;

    @Autowired
    private UserService userService;

    @Override
    public Instructor getByUserName(String userName) throws Exception {
        Instructor instructor = instructorDao.findByUserName(userName);
        if (null != instructor) {
            User user = userService.getByUserName(userName);
            instructor.setFirstName(user.getFirstName());
            instructor.setLastName(user.getLastName());
            instructor.setEmail(user.getEmail());
        }
        logger.info("Instructor information by username: " + instructor.getFirstName());
        return instructor;
    }

    @Override
    public Instructor getByEmail(String email) throws Exception {
        User user = userService.getByEmail(email);
        if (user == null) {
            logger.info(email + " not found");
            return null;
        }
        Instructor instructor = instructorDao.findByUserName(user.getUserName());
        if (null != instructor) {
            instructor.setFirstName(user.getFirstName());
            instructor.setLastName(user.getLastName());
            instructor.setEmail(user.getEmail());
        }
        return instructor;
    }

    @Override
    public int createInstructor(Instructor instructor) throws Exception {
        int status = instructorDao.save(instructor);
        return status;
    }
}
