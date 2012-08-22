package ru.testhf.srv3.h37945.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.testhf.srv3.h37945.domain.User;
import ru.testhf.srv3.h37945.domain.lists.UserList;
import ru.testhf.srv3.h37945.forms.LoginForm;
import ru.testhf.srv3.h37945.service.dao.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    protected UserService userService;

    @ModelAttribute
    public UserList getUsers() {
        List<User> users = userService.userList();
        UserList userList = new UserList(users);
        return userList;
    }

    @RequestMapping(value = "/deleteuser/", method = RequestMethod.GET)
    public String deleteUser(ModelMap model) {
        LoginForm loginForm = new LoginForm();
        model.put("loginForm", loginForm);
        return "adminPages/deleteUser";
    }

    @RequestMapping(value = "/deleteuser/", method = RequestMethod.POST)
    public String checkDeletedUser(LoginForm loginForm, ModelMap model) {
        if (!loginForm.getLogin().equals("")) {
            try {
                userService.deleteUser(loginForm.getLogin());
                return "adminPages/successfulDeleteUser";
            } catch (Exception e) {
                model.put("Error", "Wrong login");
                loginForm.setLogin("");
            }
        }
        return "adminPages/deleteUser";
    }

    @RequestMapping(value = "/updateuser/", method = RequestMethod.GET)
    public String updateUser(ModelMap model) {
        LoginForm loginForm = new LoginForm();
        model.put("loginForm", loginForm);
        return "adminPages/updateUser";
    }

    @RequestMapping(value = "/updateuser/", method = RequestMethod.POST)
    public String checkUpdateUser(LoginForm loginForm, ModelMap model) {
        if (!loginForm.getLogin().equals("")) {
            try {
                userService.updateUser(loginForm.getLogin());
                return "adminPages/successfulUpdateUser";
            } catch (Exception e) {
                model.put("Error", "Wrong login");
                loginForm.setLogin("");
            }
        }
        return "adminPages/updateUser";
    }
}
