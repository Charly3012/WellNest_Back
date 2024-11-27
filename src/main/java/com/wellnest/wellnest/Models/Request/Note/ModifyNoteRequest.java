package com.wellnest.wellnest.Models.Request.Note;

import jakarta.validation.constraints.NotBlank;

public record ModifyNoteRequest(

        @NotBlank (message = "Content must be not empty")
        String content

) {
}
