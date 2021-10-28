package com.example.basketballgurus.repositories;

import com.example.basketballgurus.models.League;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LeaguesRepository extends JpaRepository<League,Integer> {
    
}
