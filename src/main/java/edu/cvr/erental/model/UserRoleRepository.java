package edu.cvr.erental.model;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRoleRepository extends JpaRepository<UserRoles, UUID> {

     List<UserRoles> findByUserId(UUID userId);

    
}
