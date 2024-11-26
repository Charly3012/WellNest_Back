package com.wellnest.wellnest.Models.Request.Note;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record InsertNoteRequest (
    @NotBlank(message = "Content must not be empty")
    @Size(max = 65535, message = "El contenido no puede exceder los 65535 caracteres")
    String content,
    @NotNull(message = "State must not be null")
    boolean state
) {
}
