package edu.cvr.erental.model;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserPropertyRegistry {

    @Autowired
    private UserPropertyRepository userPropertyRepository;

    public UserProperty save(UserProperty userproperty) {
        return userPropertyRepository.save(userproperty);
    }

    public Page<UserProperty> getOwnedProperties(UUID userId,Pageable page)
    {
        return userPropertyRepository.findByUserIdAndStatus(userId,"owner",page);

    }

    public Optional<UserProperty> getBookedProperties(UUID userId,UUID propertyId)
    {
        return userPropertyRepository.findByUserIdAndPropertyIdAndStatus(userId,propertyId,"Booked");
    }

    public Page<UserProperty> findByPropertyIdAndStatus(UUID propertyId,String status,Pageable page)
    {
        return userPropertyRepository.findByPropertyIdAndStatus(propertyId, status,page);
    }

    
}
