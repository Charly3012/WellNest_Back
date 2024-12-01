package com.wellnest.wellnest.Repository;

import com.wellnest.wellnest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    List<User> findByNameContainingOrNicknameContaining(String name, String nickname);

}
