package com.wellnest.wellnest.Models.Request.Post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record InsertPostRequest(
        @NotBlank(message = "post content must be not empty")
        @Size(max = 280, message = "El contenido no puede exceder los 280 caracteres")
        String postContent,
        @NotBlank(message = "post content must be not empty")
        String mood

) {
}
