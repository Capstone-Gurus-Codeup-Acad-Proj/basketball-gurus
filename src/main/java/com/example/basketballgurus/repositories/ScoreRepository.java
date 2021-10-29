package com.example.basketballgurus.repositories;

import com.example.basketballgurus.models.Player;
import com.example.basketballgurus.models.PlayerScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScoreRepository extends JpaRepository<PlayerScore, Integer> {

    List<PlayerScore> getByPlayerId(Player player);

}
