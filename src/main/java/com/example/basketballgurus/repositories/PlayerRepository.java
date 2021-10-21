package com.example.basketballgurus.repositories;

import com.example.basketballgurus.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("select e from Player e where lower(e.firstName) like lower(concat('%', :search, '%')) " +
            "or lower(e.lastName) like lower(concat('%', :search, '%'))")
    List<Player> findByFirstNameOrLastName(@Param("search") String search);
}
