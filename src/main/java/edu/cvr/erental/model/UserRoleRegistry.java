package edu.cvr.erental.model;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserRoleRegistry {

    @Autowired
    private UserRoleRepository userRolesRepository;
    public UserRoles save(UserRoles roles) {
        return userRolesRepository.save(roles);
    }

     public List<UserRoles> findByUserId(UUID userId)
     {
        return userRolesRepository.findByUserId(userId);
     }
    
}
