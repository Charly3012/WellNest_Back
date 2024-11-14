package com.wellnest.wellnest.Models.DTOs;

import java.util.Date;

public record DTOCreateUser(
        String name,
        String nickname,
        String email,
        String password,
        Date bornDate
)
{

}
