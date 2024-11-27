package com.wellnest.wellnest.Models.Responses.Post;

import com.wellnest.wellnest.Models.Post;

import java.util.Date;

public record PostResponse(
        Long id,
        Date date,
        String postContent,
        String mood

) {

    public PostResponse(Post post){
        this(post.getIdPost(), post.getPostDate(), post.getPostContent(),post.getMood());
    }


}
