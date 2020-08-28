package com.csci5308.groupme.course.courseadmin.teaching_assistant.controller;

import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseDetailsService;
import constants.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class TeachingAssistantController {

    Logger logger = LoggerFactory.getLogger(TeachingAssistantController.class);

    @Autowired
    CourseDetailsService courseDetailsService;

    @GetMapping("/tahomepage")
    public ModelAndView showStudentHomePage(
            @RequestParam(value = "isStudent", required = false, defaultValue = "false") boolean isStudent,
            @RequestParam(value = "isTA", required = false, defaultValue = "false") boolean isTA,
            @RequestParam(value = "isInstructor", required = false, defaultValue = "false") boolean isInstructor,
            Principal principal) throws Exception {
        String userName = principal.getName();
        String roleName = Roles.TA;
        List<Course> managedCourses = courseDetailsService.getCoursesByUserNameAndRole(userName, roleName);
        logger.info("username: " + userName + " and user's courses: " + managedCourses);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ta/tahomepage");
        if (null != managedCourses) {
            modelAndView.addObject("managedCourses", managedCourses);
        } else {
            modelAndView.addObject("managedCourses", null);
        }
        modelAndView.addObject("roleName", roleName);
        modelAndView.addObject("isTA", true);
        return modelAndView;
    }

    @RequestMapping(value = "/TAcoursepage", method = RequestMethod.GET)
    public ModelAndView getCoursesByUserNameAndRole(Principal p, @RequestParam(value = "roleName") String roleName) throws Exception {
        String userName = p.getName();
        List<Course> courseTACourses = courseDetailsService.getCoursesByUserNameAndRole(userName, roleName);
        logger.info("username: " + userName + " and user's courses: " + courseTACourses);
        ModelAndView modelAndView = new ModelAndView();
        if (null != courseTACourses) {
            modelAndView.addObject("courses", courseTACourses);
            modelAndView.addObject("roleName", roleName);
        } else {
            modelAndView.addObject("courses", null);
            modelAndView.addObject("roleName", roleName);
        }
        modelAndView.setViewName("CourseAdmin");
        return modelAndView;
    }
}
