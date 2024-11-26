package com.wellnest.wellnest.Models.Request;

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

    public String name;
    public String nickname;
    public String email;
    public String password;
    public Date bornDate;
}
