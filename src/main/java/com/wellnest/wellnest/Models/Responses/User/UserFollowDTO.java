package com.wellnest.wellnest.Models.Responses.User;

import com.wellnest.wellnest.Models.User;

/***
 * This record is used to manage the profiles followed, avoiding recursion by showing only the necessary data.
 */
public record UserFollowDTO(
        long idUser,
        String name,
        String nickname
) {

    public UserFollowDTO (User user) {
        this(user.getIdUser(), user.getName(), user.getNickname());
    }
}
