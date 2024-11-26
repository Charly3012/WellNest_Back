package com.wellnest.wellnest.Models.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Name must not be empty")
    public String name;
    @NotBlank(message = "Nickname must not be empty")
    public String nickname;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "The format is not correct, must be like 'example@domain.com'")
    public String email;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    public String password;
    public Date bornDate;
}
