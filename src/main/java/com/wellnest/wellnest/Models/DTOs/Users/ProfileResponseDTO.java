package com.wellnest.wellnest.Models.DTOs.Users;

import com.wellnest.wellnest.Models.User;

import java.util.Date;

public record ProfileResponseDTO (
        long idUser,
        String name,
        String nickname,
        String email,
        Date bornDate
){
    public ProfileResponseDTO(User user) {
        this(user.getIdUser(), user.getName(), user.getNickname(), user.getEmail(), user.getBornDate());
    }
}
