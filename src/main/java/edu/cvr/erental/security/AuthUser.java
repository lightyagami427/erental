package edu.cvr.erental.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import edu.cvr.erental.model.ErentalUsers;
import edu.cvr.erental.model.UserRoleRegistry;
import edu.cvr.erental.model.UserRoles;
import lombok.Getter;
@Getter

public class AuthUser implements UserDetails {

    // @Autowired
    // private UserRoleRegistry 


    private ErentalUsers erusers;
    private UserRoleRegistry userRoleRegistry;
    AuthUser(ErentalUsers erusers2,UserRoleRegistry userRoleRegistry)
    {
        this.erusers=erusers2;
        this.userRoleRegistry=userRoleRegistry;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
        List<UserRoles> list=userRoleRegistry.findByUserId(erusers.getId());

        return list.stream().map(ur->new AppGrantedAuthority(ur.getRole())).toList();
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
        return erusers.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
        return erusers.getUserName();
    }
    
    
}
