package com.wellnest.wellnest.Controller;

import com.wellnest.wellnest.Models.Responses.User.ProfileResponseDTO;
import com.wellnest.wellnest.Models.User;
import com.wellnest.wellnest.Repository.UserRepository;
import com.wellnest.wellnest.Services.JwtService;
import com.wellnest.wellnest.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("getProfile")
    public ResponseEntity<ProfileResponseDTO> getCurrentUser(@RequestHeader("Authorization") String token){
        var user = userService.getUserFromToken(token);
        return ResponseEntity.ok(new ProfileResponseDTO(user));
    }

    @PostMapping("follow/{idUser}")
    public ResponseEntity<Void> addFollow(@RequestHeader("Authorization") String token,
                                    @PathVariable int idUser){
        userService.addFollow(token, idUser);
        return ResponseEntity.ok().build();
    }


}
