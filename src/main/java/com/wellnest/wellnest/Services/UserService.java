package com.wellnest.wellnest.Services;

import com.wellnest.wellnest.Models.Follow;
import com.wellnest.wellnest.Models.Note;
import com.wellnest.wellnest.Models.User;
import com.wellnest.wellnest.Repository.FollowRepository;
import com.wellnest.wellnest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private JwtService jwtService;


    private User getUser(long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ExpressionException("Usuario no encontrado con id: " + id));
    }

    public User getUserFromToken(String token){
        var id = jwtService.getUserIdFromToken(token);

        return userRepository.findById(id)
                .orElseThrow(() -> new ExpressionException("Usuario no encontrado con id: " + id));
    }

    public void addFollow(String token, int idUser) {
        Long currenUserId = jwtService.getUserIdFromToken(token);
        Follow follow = new Follow(getUser(currenUserId), getUser(idUser));
        followRepository.save(follow);
    }
}
