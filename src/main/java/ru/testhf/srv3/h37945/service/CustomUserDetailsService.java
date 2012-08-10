package ru.testhf.srv3.h37945.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userManager.getUser(s);
    }
}
