package com.example.tp3_progavanzada.services;


import com.example.tp3_progavanzada.interfaces.Loginable;
import com.example.tp3_progavanzada.interfaces.Registerable;
import com.example.tp3_progavanzada.models.UserModel;
import com.example.tp3_progavanzada.repositories.UserRepository;
import com.example.tp3_progavanzada.util.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements Registerable, Loginable {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserModel register(UserModel user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("The username is already in use");
        }

        if (!PasswordValidator.isValid(user.getPassword())) {
            throw new RuntimeException("Password must be at least 6 characters and include uppercase, lowercase, digit and special character");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserModel login(UserModel user) {
        UserModel foundUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        return foundUser;
    }
}