package com.example.basketballgurus.repositories;

import com.example.basketballgurus.models.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface LeaguesRepository extends JpaRepository<League,Integer> {
    League getByUuid(String uuid);
}
