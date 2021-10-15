package com.example.basketballgurus.repositories;

import com.example.basketballgurus.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {


}
