package com.wellnest.wellnest.Controller;

import com.wellnest.wellnest.Models.DTOs.DTOCreateUser;
import com.wellnest.wellnest.Models.User;
import com.wellnest.wellnest.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    @PostMapping(value = "insertUser")
    public String insertUser(@RequestBody DTOCreateUser dtoCreateUser) {
        User user = new User(dtoCreateUser);
        userRepository.save(user);
        return "ola bola";
    }

    @GetMapping(value = "{id}")
    public User getUser(@PathVariable long id) {
        User user= userRepository.getReferenceById(id);
        return user;
    }

    @GetMapping(value = "hola")
    public String hola() {
        return "Huevos";
    }

}
