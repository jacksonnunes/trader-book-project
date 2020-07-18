package com.traderbook.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.traderbook.domains.Users;
import com.traderbook.repositories.UserRepository;

@Service
public class UsersDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Users user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		
		//Auxilia o Spring Security a verificar qual o perfil do usuário que fez o login
		Set<GrantedAuthority> profiles = new HashSet<GrantedAuthority>();
		profiles.add(new SimpleGrantedAuthority(user.getRole()));
		return new User(user.getEmail(), user.getPassword(), profiles);
		
	}

}
