package edu.cvr.erental.model;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PropertyImagesRepository extends JpaRepository<PropertyImages, UUID> {

    List<PropertyImages> findByPropertyId(UUID propertyId);
    
}
