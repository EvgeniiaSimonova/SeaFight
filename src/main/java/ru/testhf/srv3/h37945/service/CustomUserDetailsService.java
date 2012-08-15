package ru.testhf.srv3.h37945.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.testhf.srv3.h37945.domain.User;
import ru.testhf.srv3.h37945.domain.UserDetailsImpl;

import java.util.Iterator;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) {
        /*try {
            User user = userService.getUserByLogin(s);
            return new UserDetailsImpl(user.getLogin(), user.getPassword(), user.getRole());
        } catch (ObjectNotFoundException e) {
            throw new UsernameNotFoundException("Could not find user with this login");
        }*/

        /*List<User> users = userService.userList();
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getLogin().equals(s)) {
                return new UserDetailsImpl(user.getLogin(), user.getPassword(), user.getRole());
            }
        }
        throw new UsernameNotFoundException(s + "not found");*/
        try {
            User user = userService.getUserByLogin(s);
            return new UserDetailsImpl(user.getLogin(), user.getPassword(), user.getRole());
        } catch (Exception e) {
            throw new UsernameNotFoundException("Could not find user with this login");
        }
    }
}
