package com.wellnest.wellnest.Controller;

import com.wellnest.wellnest.Models.User;
import com.wellnest.wellnest.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    @GetMapping(value = "getUsers")
    public List<User> getUsers()
    {
        List<User> users;

        users = userRepository.findAll();
        return users;
    }


}
