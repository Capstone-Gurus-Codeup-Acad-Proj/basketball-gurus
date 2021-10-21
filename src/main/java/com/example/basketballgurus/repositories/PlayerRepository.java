package com.example.basketballgurus.repositories;

import com.example.basketballgurus.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByFirstName(String firstName);

    List<Player> findByLastName(String lastName);

}
