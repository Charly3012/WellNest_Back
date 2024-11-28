package com.wellnest.wellnest.Models.Request.Post;

import jakarta.validation.constraints.NotBlank;

public record ModifyPostRequest(
        @NotBlank(message = "Content must be not empty")
        String postContent,
        @NotBlank (message = "Content must be not empty")
        String mood
) {
}
