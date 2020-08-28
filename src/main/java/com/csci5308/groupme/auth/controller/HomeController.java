package com.csci5308.groupme.auth.controller;

import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseDetailsService;
import com.csci5308.groupme.user.model.User;
import com.csci5308.groupme.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    CourseDetailsService courseDetailsService;

    @GetMapping("/")
    public String applicationPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "auth/index";
    }

    @GetMapping("/admin")
    public ModelAndView adminHomePage(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("admin/home_admin");
        modelAndView.addObject("userName", principal.getName());
        return modelAndView;
    }

    @GetMapping("/guest")
    public ModelAndView guestUserHomePage(Principal principal) throws Exception {
        ModelAndView modelAndView = new ModelAndView("guest/home_guest");
        List<Course> guestCourses = courseDetailsService.findAllCourses();
        if (guestCourses != null) {
            modelAndView.addObject("details", guestCourses);
        } else {
            modelAndView.addObject("details", null);
        }
        modelAndView.addObject("userName", principal.getName());
        return modelAndView;
    }

    @GetMapping("/home")
    public String toolUserHomePage(
            @RequestParam(value = "isStudent", required = false, defaultValue = "false") boolean isStudent,
            @RequestParam(value = "isTA", required = false, defaultValue = "false") boolean isTA,
            @RequestParam(value = "isInstructor", required = false, defaultValue = "false") boolean isInstructor) {
        if (isStudent == true && isTA == false && isInstructor == false) {
            return "redirect:/studenthomepage";
        } else if (isStudent == true && isTA == true && isInstructor == false) {
            return "redirect:/studenthomepage?isTA=true";
        } else if (isStudent == true && isTA == false && isInstructor == true) {
            return "redirect:/studenthomepage?isInstructor=true";
        } else if (isStudent == false && isTA == true && isInstructor == false) {
            return "redirect:/tahomepage";
        } else if (isStudent == false && isTA == false && isInstructor == true) {
            return "redirect:/instructorhomepage";
        } else if (isStudent == false && isTA == true && isInstructor == true) {
            return "redirect:/instructorhomepage?isTA=true";
        } else if (isStudent == true && isTA == true && isInstructor == true) {
            return "redirect:/studenthomepage?isTA=true&isInstructor=true";
        }
        return null;
    }
}
