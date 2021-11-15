package com.example.basketballgurus.repositories;

import com.example.basketballgurus.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
}