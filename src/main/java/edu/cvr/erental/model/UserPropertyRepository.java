package edu.cvr.erental.model;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository


public interface UserPropertyRepository extends JpaRepository<UserProperty, UUID>  {

    Page<UserProperty> findByUserIdAndStatus(UUID userId,final String status,Pageable page);

    Optional<UserProperty> findByUserIdAndPropertyIdAndStatus(UUID userId,UUID propertyId,String status);

    Page<UserProperty> findByPropertyIdAndStatus(UUID propertyId,String status,Pageable page);    

}
