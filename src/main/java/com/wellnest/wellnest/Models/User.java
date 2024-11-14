package com.wellnest.wellnest.Models;

import com.wellnest.wellnest.Models.DTOs.DTOCreateUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "users")
@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long idUser;
    public String name;
    public String nickname;
    public String email;
    public String password;
    public Date bornDate;



    public User(DTOCreateUser dtoCreateUser) {
        this.name = dtoCreateUser.name();
        this.nickname = dtoCreateUser.nickname();
        this.email = dtoCreateUser.email();
        this.password = dtoCreateUser.password();
        this.bornDate = dtoCreateUser.bornDate();
    }
}
