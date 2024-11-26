package com.wellnest.wellnest.Services;

import com.wellnest.wellnest.Models.User;
import com.wellnest.wellnest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(long id){

        var user = userRepository.findById(id);
        if (user.isPresent()){
            return user.get();
        }

        return null;
    }
}
