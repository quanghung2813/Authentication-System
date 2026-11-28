package com.example.authentication_system.entity;

import com.example.authentication_system.utils.Constance;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "password_reset_tokens", uniqueConstraints = @UniqueConstraint(name = Constance.UK_RESET_TOKEN_HASH, columnNames = "token_hash"))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasswordResetTokens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_reset_tokens_user"))
    User user;
    @Column(name = "token_hash", nullable = false)
    String token;
    @Column(nullable = false)
    boolean used;
    @Column(name = "expires_at", nullable = false)
    Date expiresAt;
    @Column(name = "created_at", nullable = false, updatable = false)
    Date createdAt;
}
