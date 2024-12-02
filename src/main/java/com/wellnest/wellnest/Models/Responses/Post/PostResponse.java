package com.wellnest.wellnest.Models.Responses.Post;

import com.wellnest.wellnest.Models.Post;

import java.util.Date;

public record PostResponse(
        Long id,
        Date date,
        String name,
        String nickname,
        String postContent,
        String mood

) {

    public PostResponse(Post post){
        this(post.getIdPost(), post.getPostDate(), post.getUser().getName(), post.getUser().getNickname(), post.getPostContent(),post.getMood());
    }


}
