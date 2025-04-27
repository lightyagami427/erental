package edu.cvr.erental.security;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor

public class AppGrantedAuthority implements GrantedAuthority {

    private String authority;
}
