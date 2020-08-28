package com.csci5308.groupme.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseController {

    @RequestMapping(value = "/operationoncourse", method = RequestMethod.GET)
    public ModelAndView showOperationsOnCourse(@RequestParam("courseCode") String courseCode,
                                               @RequestParam("courseName") String courseName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("coursedetails");
        modelAndView.addObject("courseCode", courseCode);
        modelAndView.addObject("courseName", courseName);
        return modelAndView;
    }

    @RequestMapping(value = "/coursepage", method = RequestMethod.GET)
    public ModelAndView getCoursePage(@RequestParam("courseName") String courseName,
                                      @RequestParam("courseCode") String courseCode) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("coursepage");
        modelAndView.addObject("courseCode", courseCode);
        modelAndView.addObject("courseName", courseName);
        return modelAndView;
    }
}
