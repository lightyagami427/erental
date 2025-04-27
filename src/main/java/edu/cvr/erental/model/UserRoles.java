package edu.cvr.erental.model;

import java.util.UUID;

import io.micrometer.common.lang.NonNull;
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
@Table(name="user_roles")

public class UserRoles {

    @Id
    @GeneratedValue
    @Column(name="id")
    private UUID id;
    @Column(name="userid")
    private UUID userId;
    @Column(name="role")
    @NonNull
    private String role;    
}
