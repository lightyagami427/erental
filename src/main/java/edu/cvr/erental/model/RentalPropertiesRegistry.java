package edu.cvr.erental.model;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@NoArgsConstructor

public class RentalPropertiesRegistry {

    @Autowired
    private RentalPropertiesRepository propertiesRepository;
    public RentalProperties save(RentalProperties property) {
        return propertiesRepository.save(property);
    }
    public Page<RentalProperties> getProperties(final String area,final int monthlyPrice,Pageable page){
        return propertiesRepository.getProperties(area,monthlyPrice,page);
    }
    public Optional<RentalProperties> findById(UUID id)
    {
        return propertiesRepository.findById(id);
    }    
}
