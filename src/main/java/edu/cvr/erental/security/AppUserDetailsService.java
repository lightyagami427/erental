package edu.cvr.erental.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.cvr.erental.model.ErentalUsers;
import edu.cvr.erental.model.ErentalUsersRepository;
import edu.cvr.erental.model.UserRoleRegistry;

public class AppUserDetailsService implements UserDetailsService{

    @Autowired
    private ErentalUsersRepository repo;
    @Autowired
    private UserRoleRegistry userRoleRegistry;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
        Optional<ErentalUsers> erusers=repo.findByUserName(username);
        if(erusers.isEmpty())
        {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        return  new AuthUser(erusers.get(),userRoleRegistry);
    }
    
}
