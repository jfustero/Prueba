package com.example.drago.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.drago.model.entity.Users;
import com.example.drago.repositories.UsersRepository;

@Service
public class CustomUserDetailsService  implements UserDetailsService{

	private final String S_ADMIN = "Admin";
	private final String S_NOOP = "{noop}";
    private UsersRepository usersRepository;

    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    	try {
	        Users user = usersRepository.findByUsername(username);
	        List<String> roles = new ArrayList<>();
	        roles.add(S_ADMIN);
	        return new User(user.getUsername(), S_NOOP + user.getPassword(), mapRolesToAuthorities(roles));
    	}catch(UsernameNotFoundException e) {
    		throw e; 
    	}
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<String> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    }
}
