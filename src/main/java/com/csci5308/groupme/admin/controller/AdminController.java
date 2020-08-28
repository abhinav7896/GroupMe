package com.csci5308.groupme.admin.controller;

import com.csci5308.groupme.admin.service.AdminService;
import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseService;
import constants.Messages;
import errors.EditCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class AdminController {

    @Autowired
    CourseService courseService;

    @Autowired
    AdminService adminService;

    @GetMapping("/admin/manageCourses")
    public ModelAndView adminLandingPage(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("admin/managecourses");
        return modelAndView;
    }

    @GetMapping("/admin/addCourse")
    public ModelAndView addCoursePage(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("admin/addcourse");
        return modelAndView;
    }

    @PostMapping("/admin/addCourse")
    public ModelAndView addCourse(@ModelAttribute("course") Course course) throws Exception {
        int status = courseService.createCourse(course);
        String message = "";
        ModelAndView modelAndView = new ModelAndView("admin/addcourse");
        if (status == EditCodes.SUCCESS) {
            message = Messages.COURSE_CREATED;
        } else if (status == EditCodes.COURSE_EXISTS) {
            message = Messages.COURSE_EXISTS;
        } else {
            message = Messages.FAILURE;
        }
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @GetMapping("/admin/deleteCourse")
    public ModelAndView deletCoursePage(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("admin/deletecourse");
        return modelAndView;
    }

    @PostMapping("/admin/deleteCourse")
    public ModelAndView deleteCourse(@ModelAttribute("course") Course course) throws Exception {
        int status = courseService.delete(course.getCourseCode());
        String message = "";
        ModelAndView modelAndView = new ModelAndView("admin/deletecourse");
        if (status >= EditCodes.SUCCESS) {
            message = Messages.COURSE_DELETED;
        } else if (status == EditCodes.COURSE_DOES_NOT_EXIST) {
            message = Messages.COURSE_DOES_NOT_EXIST;
        } else {
            message = Messages.FAILURE;
        }
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @GetMapping("/admin/manageInstructors")
    public ModelAndView getManageInstructorsPage() {
        ModelAndView modelAndView = new ModelAndView("admin/manageinstructors");
        return modelAndView;
    }

    @GetMapping("/admin/createClass")
    public ModelAndView getAddInstructorToCoursePage() {
        ModelAndView modelAndView = new ModelAndView("admin/addinstructortocourse");
        return modelAndView;
    }

    @PostMapping("/admin/createClass")
    public ModelAndView addInstructorToCourse(@RequestParam("email") String emailId,
                                              @RequestParam("courseCode") String courseCode) throws Exception {
        String message;
        int status = adminService.assignInstructorToCourse(emailId, courseCode);
        if (status == EditCodes.CLASS_ALREADY_CREATED) {
            message = Messages.CLASS_ALREADY_CREATED;
        } else if (status == EditCodes.COURSE_DOES_NOT_EXIST) {
            message = Messages.COURSE_DOES_NOT_EXIST;
        } else if (status == EditCodes.SUCCESS) {
            message = Messages.INSTRUCTOR_ASSIGNED;
        } else if (status == EditCodes.INSTRUCTOR_NOT_CREATED) {
            message = Messages.INSTRUCTOR_NOT_CREATED;
        } else if (status == EditCodes.EMAIL_DOES_NOT_EXIST) {
            message = Messages.EMAIL_DOES_NOT_EXIST;
        } else {
            message = Messages.FAILURE;
        }
        ModelAndView modelAndView = new ModelAndView("admin/addinstructortocourse");
        modelAndView.addObject("message", message);
        return modelAndView;
    }

}
