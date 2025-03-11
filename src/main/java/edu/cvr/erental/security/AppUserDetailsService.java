package edu.cvr.erental.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.cvr.erental.model.ErentalUsers;
import edu.cvr.erental.model.ErentalUsersRepository;

public class AppUserDetailsService implements UserDetailsService{

    private ErentalUsersRepository repo;
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
        return  new AuthUser(erusers.get());
    }
    
}
