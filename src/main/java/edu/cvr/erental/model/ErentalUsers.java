package edu.cvr.erental.model;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="erental_users")

public class ErentalUsers {
    @Id
    @GeneratedValue
    @Column(name="id")
    private UUID id;
    @Column(name="username")
    @Nonnull
    private String userName;
    @Column(name="email")
    @Nonnull
    private String email;
    @Column(name="password")
    @Nonnull
    private String password;
}
