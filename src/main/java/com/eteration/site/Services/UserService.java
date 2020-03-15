package com.eteration.site.Services;

import com.eteration.site.Model.User;
import com.eteration.site.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void save(User admin) {
        userRepository.save(admin);
    }

    public Optional<User> findByEmail(String email) {
       return userRepository.findByEmail(email);
    }
}
