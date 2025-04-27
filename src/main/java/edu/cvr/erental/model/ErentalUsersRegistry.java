package edu.cvr.erental.model;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@NoArgsConstructor

public class ErentalUsersRegistry {
    @Autowired
    private ErentalUsersRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ErentalUsers save(ErentalUsers user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public Optional<ErentalUsers> findByUserName(final String userName)
    {
        return userRepository.findByUserName(userName);
    }
    public Optional<ErentalUsers> findById(UUID id)
    {
        return userRepository.findById(id);
    }
}
