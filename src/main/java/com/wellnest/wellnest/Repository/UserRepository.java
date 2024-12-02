package com.wellnest.wellnest.Repository;

import com.wellnest.wellnest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM user u WHERE u.name LIKE %:query% OR u.nickname LIKE %:query%")
    List<String> findNicknamesByQuery(@Param("query") String query);

    @Query("select u from user u where u.nickname like concat('%', :nickname, '%')")
    List<User> findByNicknameContains(@Param("nickname") String nickname);

}
