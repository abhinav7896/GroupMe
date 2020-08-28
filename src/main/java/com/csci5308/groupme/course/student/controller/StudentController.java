package com.csci5308.groupme.course.student.controller;

import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    CourseDetailsService courseDetailsService;

    @GetMapping("/studenthomepage")
    public ModelAndView showStudentHomePage(
            @RequestParam(value = "isStudent", required = false, defaultValue = "false") boolean isStudent,
            @RequestParam(value = "isTA", required = false, defaultValue = "false") boolean isTA,
            @RequestParam(value = "isInstructor", required = false, defaultValue = "false") boolean isInstructor,
            Principal principal) throws Exception {
        String studentUserName = principal.getName();
        List<Course> coursesStudentEnrolledIn = courseDetailsService.findCoursesByStudentUserName(studentUserName);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("student/studenthomepage");
        if (null != coursesStudentEnrolledIn) {
            modelAndView.addObject("studentCourseDetails", coursesStudentEnrolledIn);
        } else {
            modelAndView.addObject("studentCourseDetails", null);
        }
        modelAndView.addObject("isTA", isTA);
        modelAndView.addObject("isInstructor", isInstructor);
        return modelAndView;
    }
}
