package edu.cvr.erental.security;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.cvr.erental.model.ErentalUsers;

public class AuthUser implements UserDetails {

    private ErentalUsers erusers;
    AuthUser(ErentalUsers erusers2)
    {
        this.erusers=erusers2;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
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
