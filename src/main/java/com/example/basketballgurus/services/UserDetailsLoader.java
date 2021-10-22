package com.example.basketballgurus.services;

import com.example.basketballgurus.models.User;
import com.example.basketballgurus.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.basketballgurus.models.UserWithRoles;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {

    private final UserRepository userDao;

    public UserDetailsLoader(UserRepository userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("I am serching by username");
        User user = userDao.findByUsername(userName);
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + userName);
        }

        return new UserWithRoles(user);
    }
}

