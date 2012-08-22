package ru.testhf.srv3.h37945.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.testhf.srv3.h37945.domain.User;
import ru.testhf.srv3.h37945.domain.UserDetailsImpl;
import ru.testhf.srv3.h37945.service.dao.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) {
        try {
            User user = userService.getUserByLogin(s);
            return new UserDetailsImpl(user.getLogin(), user.getPassword(), user.getRole());
        } catch (Exception e) {
            throw new UsernameNotFoundException("Could not find user with this login");
        }
    }
}
