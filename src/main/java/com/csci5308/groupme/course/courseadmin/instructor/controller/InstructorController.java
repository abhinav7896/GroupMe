package com.csci5308.groupme.course.courseadmin.instructor.controller;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.course.courseadmin.instructor.service.InstructorService;
import com.csci5308.groupme.course.courseadmin.teaching_assistant.service.TeachingAssistantService;
import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseDetailsService;
import constants.Messages;
import constants.Roles;
import errors.EditCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class InstructorController {

    @Autowired
    InstructorService instructorService;

    @Autowired
    CourseDetailsService courseDetailsService;

    TeachingAssistantService teachingAssistantService;

    @RequestMapping(value = "/instructorhomepage", method = RequestMethod.GET)
    public ModelAndView instructorHomePage(Principal principal, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("instructor/instructorhomepage");
        modelAndView.addObject("isInstructor", true);
        modelAndView.addObject("roleName", Roles.INSTRUCTOR);
        try {
            List<Course> coursesList = courseDetailsService.findCoursesByInstructor(principal.getName());
            model.addAttribute("courses", coursesList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/instructor/courseAdminPage", method = RequestMethod.GET)
    public String courseAdminPage(Principal principal, Model model, @RequestParam("roleName") String roleName) {
        try {
            List<Course> coursesList = courseDetailsService.findCoursesByInstructor(principal.getName());
            model.addAttribute("courses", coursesList);
            model.addAttribute("roleName", roleName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "CourseAdmin";
    }

    @RequestMapping(value = "/courseoperation", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView insertByTAEmailId(@RequestParam(value = "email") String emailTA,
                                          @RequestParam(value = "courseCode") String courseCode, Principal p) throws Exception {
        teachingAssistantService = SystemConfig.instance().getTeachingAssistantService();
        int assignmentConfirmation = teachingAssistantService.assignTAToCourse(emailTA, courseCode);
        ModelAndView modelAndView = new ModelAndView();
        if (assignmentConfirmation == EditCodes.SUCCESS) {
            modelAndView.addObject("status", Messages.TA_ASSIGNED);
        } else if (assignmentConfirmation == EditCodes.EMAIL_DOES_NOT_EXIST) {
            modelAndView.addObject("status", Messages.EMAIL_DOES_NOT_EXIST);
        } else {
            modelAndView.addObject("status", Messages.TA_ASSIGNED);
        }
        modelAndView.setViewName("coursedetails");
        return modelAndView;
    }

    @RequestMapping(value = "/courseAdmin/course", method = RequestMethod.GET)
    public String courseAdmin(@RequestParam("courseCode") String courseCode,
                              @RequestParam("courseName") String courseName, @RequestParam("courseCrn") String courseCrn, Model model) {
        try {
            model.addAttribute("courseCode", courseCode);
            model.addAttribute("courseName", courseName);
            model.addAttribute("courseCrn", courseCrn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "coursedetails";
    }

}
