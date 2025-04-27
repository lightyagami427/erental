package edu.cvr.erental.model;

import java.util.Optional;
import java.util.UUID;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface RentalPropertiesRepository extends JpaRepository<RentalProperties, UUID> {
    @Query("select rp from RentalProperties rp where rp.area=:area and rp.monthlyPrice<=:monthlyPrice")
    Page<RentalProperties> getProperties(@Param("area")final String area,@Param("monthlyPrice")final int monthlyPrice, Pageable page);
    

}
