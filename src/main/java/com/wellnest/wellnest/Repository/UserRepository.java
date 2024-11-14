package com.wellnest.wellnest.Repository;

import com.wellnest.wellnest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
