package com.example.basketballgurus.repositories;

import com.example.basketballgurus.models.Roster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RosterRepository extends JpaRepository<Roster, Integer> {
}
