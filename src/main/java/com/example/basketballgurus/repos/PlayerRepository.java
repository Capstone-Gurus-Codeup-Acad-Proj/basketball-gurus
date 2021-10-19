package com.example.basketballgurus.repos;

import com.example.basketballgurus.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}

