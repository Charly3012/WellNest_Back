package com.wellnest.wellnest.Models.Responses.User;

import com.wellnest.wellnest.Models.User;

public record SearchByNicknameResponse (
        long id,
        String nickname,
        int followers,
        int followings
) {
    public SearchByNicknameResponse(User user) {
        this(user.getIdUser(), user.getNickname(), user.getFollowers().size(), user.getFollowing().size());
    }

}
