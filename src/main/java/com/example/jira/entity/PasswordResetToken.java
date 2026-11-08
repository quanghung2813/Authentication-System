package com.example.jira.entity;

import com.example.jira.utils.Constance;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(name = Constance.UK_REFRESH_TOKENS_HASH, columnNames = "token_hash"))
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_reset_tokens_user"))
    private User userId;
    @Column(name = "token_hash", nullable = false)
    private String token;
    @Column(nullable = false)
    private boolean used;
    @Column(name = "expires_at", nullable = false)
    private Date expiresAt;
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
}
