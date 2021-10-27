package com.example.basketballgurus.repositories;

import com.example.basketballgurus.models.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface LeaguesRepository extends JpaRepository<League,Integer> {
    
}
