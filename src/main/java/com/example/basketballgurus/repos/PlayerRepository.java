package com.example.basketballgurus.repos;

import com.example.basketballgurus.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}

