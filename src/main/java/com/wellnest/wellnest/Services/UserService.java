package com.wellnest.wellnest.Services;

import com.wellnest.wellnest.Models.User;
import com.wellnest.wellnest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public User getUser(long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ExpressionException("Usuario no encontrado con id: " + id));
    }

    public User getUserFromToken(String token){
        var id = jwtService.getUserIdFromToken(token);

        return userRepository.findById(id)
                .orElseThrow(() -> new ExpressionException("Usuario no encontrado con id: " + id));
    }

}
