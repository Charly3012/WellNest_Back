package com.wellnest.wellnest.Controller;

import com.wellnest.wellnest.Models.User;
import com.wellnest.wellnest.Repository.UserRepository;
import com.wellnest.wellnest.Services.JwtService;
import com.wellnest.wellnest.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserService userService;
    @Autowired
    private JwtService jwtService;

    @GetMapping(value = "getUsers")
    public List<User> getUsers()
    {
        List<User> users;

        users = userRepository.findAll();
        return users;
    }

    @GetMapping("getProfile")
    public ResponseEntity<User> getCurrentUser(@RequestHeader("Authorization") String token){

        String jwt = token.replace("Bearer ", "");

        System.out.println(jwt);
        long id = jwtService.getUserId(jwt);
        System.out.println(id);
        var user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }


}
