package com.example.basketballgurus.repositories;

import com.example.basketballgurus.models.Game;
import com.example.basketballgurus.models.Player;
import com.example.basketballgurus.models.PlayerScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerScoreRepository extends JpaRepository<PlayerScore, Integer> {

    List<PlayerScore> findPlayerScoreByGameApiId(Integer game);



}
