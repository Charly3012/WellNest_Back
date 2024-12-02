package com.wellnest.wellnest.Repository;

import com.wellnest.wellnest.Models.Binnacle;
import com.wellnest.wellnest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BinnacleRepository extends JpaRepository<Binnacle, Long> {

    List<Binnacle> findAllByUser(User user);

    @Query(value = "select * from binnacles b where b.idUser = :idUser", nativeQuery = true)
    List<Binnacle> findAllByUserQuery(@Param("idUser") long idUser);

    @Override
    Optional<Binnacle> findById(Long aLong);
}
