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
@Table(name = "refresh_tokens", uniqueConstraints = @UniqueConstraint(name = Constance.UK_REFRESH_TOKENS_HASH, columnNames = "token_hash"))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshTokens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_refresh_tokens_user"))
    User user;
    @Column(name = "token_hash", nullable = false)
    String token;
    @Column(name = "family_id", nullable = false)
    String familyId;
    @Column(nullable = false)
    boolean revoked;
    @Column(name = "expires_at", nullable = false)
    Date expiresAt;
    @Column(name = "created_at", nullable = false, updatable = false)
    Date createdAt;
}
