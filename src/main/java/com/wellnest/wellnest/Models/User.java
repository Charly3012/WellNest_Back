package com.wellnest.wellnest.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"email"}),
                @UniqueConstraint(columnNames = {"nickname"})
        }
)
@Entity(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idUser")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    public long idUser;
    @Column(name = "name")
    public String name;
    @Column(unique = true, name = "nickname")
    public String nickname;
    @Column(unique = true, name = "email")
    public String email;
    @Column(name = "password")
    public String password;
    @Column(name = "bornDate")
    public Date bornDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "userRole")
    public UserRole role;

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Follow> following;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Follow> followers;


    //JTW methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "role=" + role +
                ", bornDate=" + bornDate +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", idUser=" + idUser +
                '}';
    }
}
