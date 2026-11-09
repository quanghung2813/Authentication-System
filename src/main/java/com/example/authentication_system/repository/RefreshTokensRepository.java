package com.example.authentication_system.repository;

import com.example.authentication_system.entity.RefreshTokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokensRepository extends JpaRepository<RefreshTokens, Long> {
}
