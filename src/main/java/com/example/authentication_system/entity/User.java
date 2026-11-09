package com.example.authentication_system.entity;

import com.example.authentication_system.enums.Roles;
import com.example.authentication_system.utils.Constance;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(name = Constance.UK_USER_MAIL, columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Email invalid")
    private String email;
    @Column(name = "password_hash", nullable = false)
    private String password;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(nullable = false)
    private boolean enabled;
    @Column(name = "password_changed_at")
    private Date passwordChangedAt;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    foreignKey = @ForeignKey(name = "fk_user_roles_user")
            )
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    @BatchSize(size = 50)
    private Set<Roles> roles;
}
