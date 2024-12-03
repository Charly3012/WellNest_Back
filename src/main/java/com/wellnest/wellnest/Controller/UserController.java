package com.wellnest.wellnest.Controller;

import com.wellnest.wellnest.Models.Request.User.ModifyUserProfile;
import com.wellnest.wellnest.Models.Responses.User.ProfileResponseDTO;
import com.wellnest.wellnest.Models.Responses.User.ProfileResponseDTO;
import com.wellnest.wellnest.Models.Responses.User.SearchByNicknameResponse;
import com.wellnest.wellnest.Models.User;
import com.wellnest.wellnest.Repository.UserRepository;
import com.wellnest.wellnest.Services.JwtService;
import com.wellnest.wellnest.Services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping(value = "getUsers")
    public List<User> getUsers()
    {
        List<User> users;

        users = userRepository.findAll();
        return users;
    }

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

    @DeleteMapping("{userId}")
    public ResponseEntity<ProfileResponseDTO> deletUser(@RequestHeader("Authorization") String token, @PathVariable Long userId){
        userService.deletUser(token,userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("modifyProfile")
    @Transactional
    public  ResponseEntity<ProfileResponseDTO> modifyUserProfile(@RequestHeader("Authorization") String token, @RequestBody ModifyUserProfile modifyRequest){
        ProfileResponseDTO profileResponse = userService.modifyUser(token, modifyRequest);
        return ResponseEntity.ok(profileResponse);
    }

    @GetMapping("searchUsersByNickname")
    public ResponseEntity<List<SearchByNicknameResponse>> searchUsers(@RequestParam String query) {
        List<SearchByNicknameResponse> usersSearched = userService.searchUsers(query);
        return ResponseEntity.ok(usersSearched);
    }


}
