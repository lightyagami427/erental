package edu.cvr.erental.model;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ErentalUsersRepository extends JpaRepository<ErentalUsers, UUID> {

    Optional<ErentalUsers> findByUserName(final String userName);
    
}
