package ru.testhf.srv3.h37945.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.testhf.srv3.h37945.dao.UserForm;
import ru.testhf.srv3.h37945.domain.User;
import ru.testhf.srv3.h37945.service.UserService;

import java.util.Date;

@Controller
public class RegistrationController {

    @Autowired
    protected UserService userService = null;

    @RequestMapping(value = "/registration/", method = RequestMethod.GET)
    public String getRegistrationPage(ModelMap model) {
        UserForm userForm = new UserForm();
        model.put("userForm", userForm);
        return "registration";
    }

    @RequestMapping(value = "/registration/", method = RequestMethod.POST)
    public String checkDataForm(UserForm userForm) {
        if (!userForm.getLogin().equals("") && userForm.getPassword().equals(userForm.getConfirmPassword())) {
            try {
                userService.saveUser(new User(userForm.getLogin(), userForm.getPassword(), "ROLE_USER"));
                return "successfulRegistration";
            } catch (org.hibernate.exception.ConstraintViolationException e) {
            }
        }
        return "registration";
    }
}