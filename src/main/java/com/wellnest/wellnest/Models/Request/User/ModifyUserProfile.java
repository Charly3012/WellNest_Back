package com.wellnest.wellnest.Models.Request.User;

import jakarta.validation.constraints.NotBlank;

public record ModifyUserProfile(
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String nickname
) {
}
