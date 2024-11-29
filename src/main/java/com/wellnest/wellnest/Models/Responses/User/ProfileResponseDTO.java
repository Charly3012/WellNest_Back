package com.wellnest.wellnest.Models.Responses.User;

import com.wellnest.wellnest.Models.Follow;
import com.wellnest.wellnest.Models.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ProfileResponseDTO (
        long idUser,
        String name,
        String nickname,
        String email,
        Date bornDate,
        Set<UserFollowDTO> following,
        Set<UserFollowDTO> followers
){
    public ProfileResponseDTO(User user) {
        this( // Delegar al constructor principal
                user.getIdUser(),
                user.getName(),
                user.getNickname(),
                user.getEmail(),
                user.getBornDate(),
                user.getFollowing().stream()
                        .map(following -> new UserFollowDTO(following.getUser()))
                        .collect(Collectors.toSet()),
                user.getFollowers().stream()
                        .map(follow -> new UserFollowDTO(follow.getFollower()))
                        .collect(Collectors.toSet())

        );
    }
}