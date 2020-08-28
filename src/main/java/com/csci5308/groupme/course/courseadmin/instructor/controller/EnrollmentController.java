package com.csci5308.groupme.course.courseadmin.instructor.controller;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.course.courseadmin.instructor.service.EnrollmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
public class EnrollmentController {

    EnrollmentService enrollmentService;

    @RequestMapping(value = "/courseAdmin/uploadfile", method = RequestMethod.POST)
    public String uploadCSV(@RequestParam("file") MultipartFile file, @RequestParam("courseCode") String courseCode,
                            Principal principal) {
        enrollmentService = SystemConfig.instance().getEnrollmentService();
        enrollmentService.upload(file, principal.getName(), courseCode);
        return "redirect:/instructor/courseAdminPage";
    }
}
