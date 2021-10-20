package com.example.basketballgurus.repositories;

import com.example.basketballgurus.models.PlayerScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerScoreRepository extends JpaRepository<PlayerScore, Integer> {



}
