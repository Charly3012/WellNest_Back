package com.wellnest.wellnest.Services;

import com.wellnest.wellnest.Models.Post;
import com.wellnest.wellnest.Models.Request.User.ModifyUserProfile;
import com.wellnest.wellnest.Models.Responses.User.ProfileResponseDTO;
import com.wellnest.wellnest.Models.Follow;
import com.wellnest.wellnest.Models.Note;
import com.wellnest.wellnest.Models.Post;
import com.wellnest.wellnest.Models.User;
import com.wellnest.wellnest.Repository.FollowRepository;
import com.wellnest.wellnest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private JwtService jwtService;

    public User getUser(long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ExpressionException("Usuario no encontrado con id: " + id));
    }

    public User validationRoleDelete(String token, Long iduser) {
        Long userId = jwtService.getUserIdFromToken(token);
        User user = getUser(userId);

        if (!(user.getIdUser() == iduser || user.getRole().toString().equals("ADMIN"))) {
            throw new SecurityException("You are not authorized to modify this note");
        }
        return userRepository.getReferenceById(iduser);
    }

    public User getUserFromToken(String token){
        var id = jwtService.getUserIdFromToken(token);

        return userRepository.findById(id)
                .orElseThrow(() -> new ExpressionException("Usuario no encontrado con id: " + id));

    }

    public void deletUser(String token, Long userId){
        User user = validationRoleDelete(token, userId);
        userRepository.delete(user);

    }

    public ProfileResponseDTO modifyUser(String token, ModifyUserProfile modifyRequest){
        Long userId = jwtService.getUserIdFromToken(token);
        User user = getUser(userId);

            user.setName(modifyRequest.name());
            user.setEmail(modifyRequest.email());
            user.setNickname(modifyRequest.nickname());
        return new ProfileResponseDTO(user);
    }

    public void addFollow(String token, int idUser) {
        Long currenUserId = jwtService.getUserIdFromToken(token);
        Follow follow = new Follow(getUser(currenUserId), getUser(idUser));
        followRepository.save(follow);
    }

    public List<User> searchUsers(String query) {
        return userRepository.findByNameContainingOrNicknameContaining(query, query);
    }


}
