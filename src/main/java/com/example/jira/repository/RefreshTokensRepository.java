package com.example.jira.repository;

import com.example.jira.entity.RefreshTokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokensRepository extends JpaRepository<RefreshTokens, Long> {
}
