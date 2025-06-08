package com.example.tp3_progavanzada.controllers;


import com.example.tp3_progavanzada.interfaces.Loginable;
import com.example.tp3_progavanzada.interfaces.Registerable;
import com.example.tp3_progavanzada.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private Registerable registerableService;

    @Autowired
    private Loginable loginableService;

    @PostMapping("/register")
    public UserModel register(@RequestBody UserModel user){
        return registerableService.register(user);
    }

    @PostMapping("/login")
    public UserModel login(@RequestBody UserModel user){
        return  loginableService.login(user);
    }

}
