package ru.testhf.srv3.h37945.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.testhf.srv3.h37945.domain.UserDetailsImpl;

@Controller
public class ProfileController {
    @ModelAttribute
    public UserDetailsImpl populateCurrentUser(){
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @RequestMapping(value = "/profile/", method = RequestMethod.GET)
    public String getProfilePage() {
        return "profile";
    }

    @RequestMapping(value = "/errors/403.html")
    public ModelAndView handle403(Model model) {

        ModelAndView modelAndView = new ModelAndView("profile");
        return modelAndView;
    }
}
